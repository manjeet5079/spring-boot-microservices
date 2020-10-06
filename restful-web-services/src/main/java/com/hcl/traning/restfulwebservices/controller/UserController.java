package com.hcl.traning.restfulwebservices.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hcl.traning.restfulwebservices.exception.UserNotFoundException;
import com.hcl.traning.restfulwebservices.model.User;
import com.hcl.traning.restfulwebservices.service.UserDaoService;

/**
 * @author Manjeet Kumar
 *
 *         Jun 26, 2020
 */

@RestController
public class UserController {

	@Autowired
	private UserDaoService userDaoService;

	
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {

		return userDaoService.findAll();
	}

	@GetMapping(value = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		 User user = userDaoService.findOne(id);
		 if(user==null) {
			 throw new UserNotFoundException("Id-"+id);
		 }
		 
		 //HATEOAS
		 //"all-users" , SERVER_PATH + "/users"
		 //retrieveAllUsers
		 
		 return user;
	}

	
	@PostMapping("/users")
	public   ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userDaoService.save(user);
		
		 URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId()).toUri();
	
		 return  ResponseEntity.created(location).build();
	}

	
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable(value="id") Integer id) {
		System.out.println("id -"+id);
		 User user = userDaoService.deleteById(id);
		 if(user==null) {
			 throw new UserNotFoundException("Id-"+id);
		 }
		 
	}
	
	
	//Internationalization
}
