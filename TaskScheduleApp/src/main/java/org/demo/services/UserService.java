package org.demo.services;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


import org.demo.entities.Role;
import org.demo.entities.User;
import org.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;




@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	
	
	
	public void createUser(User user)   {
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword())); 
		Role userRole = new Role("USER");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		user.setActive(true);
		
		userRepository.save(user);
		 } 
	
	public void createAdmin(User user) {
		String pwd = user.getPassword();
		BCryptPasswordEncoder  encoder = new  BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword())); 
		System.out.println("encoded password"+ user.getPassword());
		Role userRole = new Role("ADMIN");
		List<Role> roles = new ArrayList<>();
		roles.add(userRole);
		user.setRoles(roles);
		user.setActive(true);
		boolean isPasswordMatch = passwordEncoder.matches(pwd, user.getPassword());
		System.out.println("de coded password"+ isPasswordMatch);
		userRepository.save(user);
	}
	
	public User findOne(String email) {
		
	  return userRepository.findOne(email);
	}

	public boolean isUserPresent(String email) {
		// TODO Auto-generated method stub
		User u=userRepository.findOne(email);
		if(u!=null)
			return true;
		
		return false;
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return  userRepository.findByNameLike("%"+name+"%");
	}

	public User findByName1(String email) {
		// TODO Auto-generated method stub
		
		return userRepository.findByEmail(email);
	
	}

	public void delete(String email) throws Exception {
	userRepository.delete(email);
	userRepository.flush();
		
	}
	

	public void getConfirm(String email) {
		// TODO Auto-generated method stub
		
		
	
	 userRepository.findByEmail(email).setActive(true);;
	 userRepository.flush();
		
		
	}

}
