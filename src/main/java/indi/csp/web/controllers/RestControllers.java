package indi.csp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import indi.csp.web.database.VOMapper;
import indi.csp.web.vo.ItemDetailVO;
import indi.csp.web.vo.ItemListVO;
import indi.csp.web.vo.UserVO;

@RestController
public class RestControllers {
	@Autowired
	private VOMapper voMapper;

	/*
	 * GET Methods
	 */

	@RequestMapping("/user/{id}")
	public UserVO getUserDetailInfo(@PathVariable("id") String id) {
		UserVO userVo = voMapper.getUserInfoBy(id);
		return userVo;
	}

	@RequestMapping("/list/{a}/{z}")
	public ItemListVO getListOfItems(@PathVariable("a") int start, @PathVariable("z") int end) {
		ItemListVO result = voMapper.getItemsByRange(start, end);
		return result;
	}

	@RequestMapping("/detail/{id}")
	public ItemDetailVO getItemDetailInfo(@PathVariable("id") String id) {
		ItemDetailVO result = voMapper.getItemDetailInfoBy(id);
		return result;
	}

	/*
	 * POST Methods
	 */

	@RequestMapping("/upload")
	public String uploadItem() {
		return "uploadItem";
	}

	@RequestMapping("/update/item")
	public String updateItemInfo() {
		return "updateItemInfo";
	}

	@RequestMapping("/update/user")
	public String updateUserInfo() {
		return "";
	}
}
