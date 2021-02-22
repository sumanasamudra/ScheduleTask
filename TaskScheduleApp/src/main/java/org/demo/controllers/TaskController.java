package org.demo.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.demo.entities.Task;
import org.demo.services.TaskService;
import org.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TaskController {

	 @Autowired
	 private TaskService taskService;
	 @Autowired
	 private UserService  userService;
	 
	 @GetMapping("/addTask")
	 public String taskForm(String email, Model model, HttpSession session) {
		 
		 session.setAttribute("email", email); 
		 model.addAttribute("task", new Task());
		 return "views/taskForm";
		 
	 }
	 
	 @PostMapping("/addTask")
	 public String addTask(@Valid Task task,BindingResult bindingResult, HttpSession session) {
		 if(bindingResult.hasErrors()) {
			 return "views/taskForm";
		 }
		 String email = (String) session.getAttribute("email");
		 taskService.addTask(task, userService.findOne(email));
		 
		return  "redirect:/users";
	 }
	 
	 @GetMapping("/addTask1")
	 public String taskForm1(String email, Model model, HttpSession session) throws Exception {
		 System.out.println(email);
		 System.out.println(email);
		 System.out.println(email);
		 System.out.println(email);
		 userService.delete(email);
//		 session.setAttribute("email", email); 
//		 model.addAttribute("task", new Task());
		 return "redirect:/users1";
		 
	 }
	 @GetMapping("/addTask2")
	 public String taskForm2(Long id, Model model, HttpSession session) throws Exception {
		
		 System.out.println(id);
		 taskService.delete(id);
//		 session.setAttribute("email", email); 
//		 model.addAttribute("task", new Task());
		 return "redirect:/profile";
		 
	 }
	 @GetMapping("/about")
	 public String taskForm12() throws Exception {
		
		 return "views/about";
		 
	 }
}
