package com.hcl.traning.restfulwebservices.controller;

import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.ControllerLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hcl.traning.restfulwebservices.dao.PostRepository;
import com.hcl.traning.restfulwebservices.dao.UserRepository;
import com.hcl.traning.restfulwebservices.exception.UserNotFoundException;
import com.hcl.traning.restfulwebservices.model.Post;
import com.hcl.traning.restfulwebservices.model.User;

/**
 * @author Manjeet Kumar
 *
 *         Jun 26, 2020
 */

@RestController
public class UserJPAController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(value = "/jpa/users/{id}")
	public Optional<User> retrieveUser(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}

		// HATEOAS
		// "all-users" , SERVER_PATH + "/users"
		// retrieveAllUsers
		// Resource changed to EntityModel
		// Resources changed to CollectionModel
		EntityModel<User> resource = new EntityModel<User>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return user;
	}

	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		User saveUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(saveUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/jpa/users/{id}")
	public void deleteById(@PathVariable(value = "id") Integer id) {
		System.out.println("id -" + id);
		userRepository.deleteById(id);

	}

	@GetMapping("/jpa/users/{id}/post")
	public List<Post> retrieveAllUsersPost(@PathVariable(value = "id") Integer id) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}
		return userOptional.get().getPosts();
	}

	@PostMapping("/jpa/users/{id}/post")
	public ResponseEntity<Object> createPost(@PathVariable(value = "id") Integer id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("Id-" + id);
		}

		User user = userOptional.get();
		post.setUser(user);

		Post savePost = postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savePost.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}
}
