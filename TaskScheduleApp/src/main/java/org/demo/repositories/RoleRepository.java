package org.demo.repositories;

import org.demo.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository  extends JpaRepository<Role, String>{
 
}
