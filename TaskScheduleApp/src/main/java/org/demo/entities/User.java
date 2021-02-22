package org.demo.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class User {

	@Id
	@Email
	@NotEmpty
	@Column(unique = true)
	
	private String email;
	@NotEmpty
	private String name;
	@NotEmpty
	@Size(min = 16)
	
	private String password;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Task> tasks;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name = "USER_ROLES", joinColumns={
			@JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_NAME", referencedColumnName = "name") })
	private List<Role> roles;
	

	private boolean active;
	
	@Column(name="valitateCode")
	private String valitateCode;
	

	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getValitateCode() {
		return valitateCode;
	}

	public void setValitateCode(String valitateCode) {
		this.valitateCode = valitateCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Task> gettasks() {
		return tasks;
	}

	public void settasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	public User() {

	}

}
