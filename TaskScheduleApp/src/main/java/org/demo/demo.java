package org.demo;

import org.demo.entities.User;
import org.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class demo  implements  CommandLineRunner{
	   @Autowired
	   private UserService userService;
	   
	     
	public static void main(String[] args) {
		SpringApplication.run(demo.class, args);
		System.out.println("<<<<<<<<< <<<<<<<<< BOOOTED >>>>>>>>> >>>>>>>>>");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		  {
    		  User newAdmin = new User("admin@mail.com", "Admin", "admin1234admin!");
    		  userService.createAdmin(newAdmin); 
    		  User newUser1 = new User("user1@mail.com", "User1", "user1234");
    		  userService.createUser(newUser1); 
    		  User newUser2 = new User("user2@mail.com", "User2", "user1234user!");
    		  userService.createUser(newUser2); 
    		  
    	  }
	}
}
