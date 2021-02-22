package org.demo.repositories;

import java.util.List;

import org.demo.entities.Task;
import org.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository  extends JpaRepository<Task, Long>{

	List<Task> findByUser(User user); 
	//List<Task> findAll(List<User> user);
	@Modifying
	@Transactional
	@Query(value="delete from Task c where c.id = ?1")
	void deleteById(Long id)  ;
	
}
