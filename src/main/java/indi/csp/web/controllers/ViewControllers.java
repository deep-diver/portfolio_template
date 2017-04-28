package indi.csp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import indi.csp.web.database.VOMapper;
import indi.csp.web.vo.ItemListVO;

@Controller
public class ViewControllers {
	@Autowired
	private VOMapper voMapper;	
	
	@RequestMapping("/")
	public ModelAndView viewOfListOfItem() {
		ItemListVO result = voMapper.getItemsByRange(0, 9);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("listContents");
		
		if (result.getTotalCount() > 0) {
			view.addObject("item_list", result.getItems());
		}
		
		return view;
	}
	
	@RequestMapping("/signup")
	public ModelAndView viewOfSignup() {
		ModelAndView view = new ModelAndView();
		return view;
	}
}
