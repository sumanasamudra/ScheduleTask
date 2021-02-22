	package org.demo.services;

import java.util.List;

import org.demo.entities.Task;
import org.demo.entities.User;
import org.demo.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public void addTask(Task task, User user) {
		task.setUser(user);
		taskRepository.save(task);
	}
	
	public List<Task>  findUserTask(User user){
		
		return taskRepository.findByUser(user);
	}
   
    public List<Task>  findAll(){
		
		return taskRepository.findAll();
	}
    public void delete(Long id) throws Exception {
    	taskRepository.delete(id);
    	taskRepository.flush();
    		
    	}
	
	


}
