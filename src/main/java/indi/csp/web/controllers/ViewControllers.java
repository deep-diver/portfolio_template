package indi.csp.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewControllers {
	@RequestMapping("/")
	public ModelAndView viewOfListOfItem() {
		ModelAndView view = new ModelAndView();
		return view;
	}
	
	@RequestMapping("/signup")
	public ModelAndView viewOfSignup() {
		ModelAndView view = new ModelAndView();
		return view;
	}
}
