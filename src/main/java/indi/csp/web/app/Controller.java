package indi.csp.web.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@RequestMapping("/")
	public String index() {
		return "Hello World";
	}
}
