package indi.csp.web.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/user/**").hasAnyRole("USER").anyRequest().authenticated()
				.antMatchers("/list/**").hasAnyRole("USER").anyRequest().authenticated()
				.antMatchers("/detail/**").hasAnyRole("USER").anyRequest().authenticated()
				.antMatchers("/upload").hasAnyRole("USER").anyRequest().authenticated()
				.antMatchers("/update/item").hasAnyRole("USER").anyRequest().authenticated()
				.antMatchers("/update/user").hasAnyRole("USER").anyRequest().authenticated()
				.antMatchers("/resources/**")
					.anonymous()
					.anyRequest()
					.permitAll()
		.and()
			.formLogin()
				.loginPage("/signin")
				.usernameParameter("id")
				.passwordParameter("password")
				.permitAll()
		.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll()
		.and()
			.csrf().disable();
	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.jdbcAuthentication()
        		.dataSource(dataSource)
        		.usersByUsernameQuery("SELECT id, password, enabled FROM user WHERE id=?")
        		.authoritiesByUsernameQuery("SELECT user_id, role FROM user_role WHERE user_id=?");
	}
}
