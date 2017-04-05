package indi.csp.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan("indi.csp.web.security")
@ComponentScan("indi.csp.web.controllers")
public class PortfolioTemplateApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioTemplateApplication.class, args);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/signin").setViewName("login");
	}
}
