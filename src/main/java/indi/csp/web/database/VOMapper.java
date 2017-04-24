package indi.csp.web.database;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import indi.csp.web.vo.ItemDetailVO;
import indi.csp.web.vo.ItemListVO;
import indi.csp.web.vo.ItemVO;
import indi.csp.web.vo.UserVO;

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
						+ "(SELECT CONCAT(MAX(iv.major), \".\", MAX(iv.minor)) FROM item_version iv WHERE iv.id = i.version) AS latest_version "
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
	
	public ItemDetailVO getDetailInfoBy(String id) {
		String sql = ""
				+ "SELECT "
				   + "i.title, "
				   + "i.description, "
				   + "concat((iv.major), \".\" , (iv.minor)) as version,"
				   + "iv.date as last_update_date"
				+ "FROM"
				    + "item_version iv, "
				   + "item i "
				+ "WHERE "
				   +"i.id = 1 /* parameters here */ "
				   +"AND "
				   +"iv.id = i.version";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("user_id", id);
		
		List<ItemDetailVO> itemDetailVOs = paramJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<ItemDetailVO>(ItemDetailVO.class));
		itemDetailVOs = paramJdbcTemplate.query(sql, params, new BeanPropertyRowMapper<ItemDetailVO>(ItemDetailVO.class));		
		
		ItemDetailVO itemDetailVo = new ItemDetailVO();
		
		itemDetailVo.setVersions(itemDetailVOs);
		itemDetailVo.setVersions(itemDetailVOs);
		itemDetailVo.setTotalCount(itemDetailVOs.size());
		
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
		
		return itemDeltailVo;		
		
		}

}
















