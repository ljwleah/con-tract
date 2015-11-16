package com.neeq.contractManagementSys.security;

import java.util.ArrayList;
import java.util.List;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;









import com.neeq.contractManagementSys.domain.Person;
import com.neeq.contractManagementSys.service.PersonService;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}
	
	@Configuration
	@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
		@Autowired
		private PersonService personService;
		@Autowired
		private SecurityProperties security;
		@Autowired
		private UserDetailsService userDetailsService;


		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().fullyAuthenticated().and().formLogin()
					.loginPage("/login").failureUrl("/login?error").permitAll().and()
					.logout().permitAll();
		}

		
		@Override
		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			
			 /*auth
             .userDetailsService(userDetailsService)
             .passwordEncoder(new BCryptPasswordEncoder());*/
			 
			/*List<Person> persons = this.personService.findAll();
			for(Person person : persons){
				
				auth.inMemoryAuthentication().withUser(person.getName()).password(person.getName())
				.roles("ADMIN", "USER");
			}*/
			
			/*auth.inMemoryAuthentication().withUser("luoxq").password("luoxq")
					.roles("ADMIN", "USER");
			*/
			auth.userDetailsService(userDetailsService);
		}

	}
}
