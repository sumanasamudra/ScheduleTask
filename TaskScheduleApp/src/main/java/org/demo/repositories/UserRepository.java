package org.demo.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository  extends JpaRepository<User, String> {
	

	List<User> findByNameLike(String name);

	User findByEmail(String email);
	


	
	@Modifying
	@Transactional
	@Query(value="delete from User c where c.email = ?1")
	void deleteByEmail(String email)  ;
	

}
