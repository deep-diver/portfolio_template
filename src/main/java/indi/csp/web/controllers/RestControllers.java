package indi.csp.web.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {
	/* 
	 * GET Methods 
	 */
	
	@RequestMapping("/user/{id}")
	public String getUserDetailInfo(@PathVariable("id") String id) {
		return "getUserDetailInfo";
	}
	
	@RequestMapping("/list/{a}/{z}")
	public String getListOfItems(@PathVariable("a") int start, 
							   @PathVariable("z") int end) {
		return "getListOfItems";
	}
	
	@RequestMapping("/detail/{id}")
	public String getItemDetailInfo(@PathVariable("id") String id) {
		return "getItemDetailInfo";
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
