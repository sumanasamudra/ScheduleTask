package org.demo.controllers;

import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.demo.entities.Task;
import org.demo.entities.User;
import org.demo.services.TaskService;
import org.demo.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	
	
	 Logger logger = LoggerFactory.getLogger(IndexController.class);
	  
	@GetMapping("/")
	public String showIndexPage(Model model) {
		
		List<User> user = userService.findAll();
		
		//model.addAttribute("task", task.getDescription());
		
		model.addAttribute("tasks", taskService.findAll());
		return "index";  
	}
	
	@GetMapping("/login") 
	public String showLoginForm() {
		
		
		
		logger.debug("iam in login");
		
		return "views/loginForm";  
	}
	
	@GetMapping("/login2") 
	public String showLoginForm1() {
		logger.info("iam in login2");
		logger.debug("iam in login");
		System.out.println("iam in login2");
		
		return "views/loginForm";  
	}
	
	
	  
	

}
