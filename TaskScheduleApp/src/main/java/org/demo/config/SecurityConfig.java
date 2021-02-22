package org.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
	
		.usersByUsernameQuery("select email, password ,active=true, true from user where email=?")
		.authoritiesByUsernameQuery("select user_email as principal, role_name as role from user_roles where user_email=?")
		
		.passwordEncoder(passwordEncoder()).rolePrefix("ROLE_");  
		
		
		
	}
   
	@Bean
	public PasswordEncoder passwordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().antMatchers("/register", "/", "/about", "/login","/confirm", "/css/**", "/webjars/**").permitAll()
				.antMatchers("/profile").hasAnyRole("USER,ADMIN")
				.antMatchers("/users1").hasRole("ADMIN")
				.antMatchers("/users","/addTask").permitAll()
				
				.and().formLogin().loginPage("/login2").permitAll()
				.defaultSuccessUrl("/profile").and().logout().logoutSuccessUrl("/login")
				.and().sessionManagement().maximumSessions(1);
		
				
		
		
	}
	
	
}
