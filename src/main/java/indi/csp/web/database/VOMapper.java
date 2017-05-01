package indi.csp.web.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import indi.csp.web.vo.ItemDetailVO;
import indi.csp.web.vo.ItemListVO;
import indi.csp.web.vo.ItemVO;
import indi.csp.web.vo.UserVO;
import indi.csp.web.vo.VersionVO;

@Repository
public class VOMapper {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate paramJdbcTemplate;
	
	public UserVO getUserInfoBy(String id) {
		String sql = ""
				+ "SELECT "
					+ "u.id,"
					+ "u.name,"
					+ "(SELECT d.title FROM department d WHERE d.id = u.department) as department,"
					+ "(SELECT ur.role FROM user_role ur WHERE ur.user_id = u.id) as role "
				+ "FROM "
					+ "user u "
				+ "WHERE "
					+ "u.id = :user_id ";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", id);
		
		List<UserVO> listOfUserVo = paramJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<UserVO>(UserVO.class));
		UserVO resultUserVo = null;
		
		if (listOfUserVo.isEmpty()) {
			resultUserVo = new UserVO();
			resultUserVo.setSuccess(false);
			resultUserVo.setTotalCount(0);
		}
		else {
			resultUserVo = listOfUserVo.get(0);
			resultUserVo.setSuccess(true);
			resultUserVo.setTotalCount(1);
		}
		
		return resultUserVo;
	}

	public ItemListVO getItemsByRange(int start, int end) {
		String sql = ""
				+ "SELECT "
					+ "A.* "
				+ "FROM ( "
					+ "SELECT "
						+ "@rownum:=@rownum+1 as row_num, "
						+ "i.id, "
						+ "i.title, "
						+ "i.description, "
						+ "(SELECT d.description FROM department d WHERE d.id = i.department) AS department, "
						+ "(SELECT CONCAT(MAX(iv.major), \".\", MAX(iv.minor)) FROM item_version iv WHERE iv.id = i.version) AS latest_version, "
						+ "(select  date_format(max(iv.date),'%Y-%m-%d') from item_version iv where iv.id = i.version) as latest_update "
					+ "FROM "
						+ "item i "
					+ "WHERE "
						+ "(@rownum:=0) = 0) as A "
				+ "WHERE "
					+ "A.row_num >= :start_range AND A.row_num <= :end_range";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("start_range", start);
		params.addValue("end_range", end);
		
		// Don't know the reason yet, why need to call query twice
		List<ItemVO> itemVos = paramJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<ItemVO>(ItemVO.class));
		itemVos = paramJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<ItemVO>(ItemVO.class));		
		ItemListVO itemListVo = new ItemListVO();
		itemListVo.setItems(itemVos);
		itemListVo.setTotalCount(itemVos.size());
		
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		System.out.println("query: " + sql);
		System.out.println("size: " + itemVos.size());
		
		if (itemVos.isEmpty()) {
			itemListVo.setSuccess(false);
			itemListVo.setStartRange(start);
			itemListVo.setEndRange(start);
		}
		else {
			itemListVo.setSuccess(true);
			itemListVo.setStartRange(start);
			itemListVo.setEndRange(start + itemVos.size());			
		}
		
		return itemListVo;
	}
	
	public ItemDetailVO getItemDetailInfoBy(String id) {
		String sql = ""
				+ "SELECT "
				   + "i.title, "
				   + "i.description, "
				   + "concat((iv.major), \".\" , (iv.minor)) as version,"
				   + "iv.date as update_date "
				+ "FROM "
				    + "item_version iv, "
				   + "item i "
				+ "WHERE "
				   +"i.id = :item_id " 
				   +"AND "
				   +"iv.id = i.version";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("item_id", id);
		
		RowMapper<ItemDetailVO> itemDetailMapper = new RowMapper<ItemDetailVO>() {
			
			@Override
			public ItemDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ItemDetailVO itemDetailVo = new ItemDetailVO();
				List<VersionVO> versionVos = new ArrayList<VersionVO>();
				int row = 0;
				
				while(rs.next()) {
					if (row == 0) {
						rs.first();
						itemDetailVo.setTitle(rs.getString("title"));
						itemDetailVo.setDescription(rs.getString("description"));
					}
					
					VersionVO versionVo = new VersionVO();
					versionVo.setDate(rs.getString("update_date"));
					versionVo.setVersion(rs.getString("version"));
					versionVos.add(versionVo);
					
					row++;
				}
				
				itemDetailVo.setVersions(versionVos);
				itemDetailVo.setTotalCount(versionVos.size());
				itemDetailVo.setSuccess(!versionVos.isEmpty());
				
				return itemDetailVo;
			}
		};
		
		ItemDetailVO itemDetailVo = paramJdbcTemplate.queryForObject(sql, params, itemDetailMapper);				
		return itemDetailVo;			
	}

}
















