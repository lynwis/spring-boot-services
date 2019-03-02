package com.harlansoft.restfulwebservices.resources.users;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.harlansoft.restfulwebservices.dao.users.UserDaoService;
import com.harlansoft.restfulwebservices.model.users.User;
import com.harlansoft.restfulwebservices.resources.users.exception.UserNotFoundException;

@RestController
public class UserResource {
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private UserDaoService userDao;
	
	@GetMapping("/hello")
	public String hello(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message", null, "i18n lookup failed!", locale);
	}
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return userDao.findAll();
	}

//	HATEOAS when i return the user, i want to return also a link
//	to the "general" /users endpoint, where the consumer can find all users
//	all-users, SERVER_PATH + /users
//	what i actually want is to dinamically get the uri associated with my endpoint, so if it changes it gets updated automatically
	@GetMapping("/users/{id}")
	public Resource<User> retrieveUser(@PathVariable @NotNull int id) {
		User user = userDao.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(String.format("User %s", id));
		}
		
		Resource<User> resource = new Resource<User>(user);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}
	
	// create new user
	// input: user details
	// output: CREATED status & the uri of the resource in the header
	@PostMapping("/users")
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri()).build();
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user) {
		userDao.save(user);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		User user = userDao.deleteById(id);
		if (user == null) throw new UserNotFoundException(String.format("User %s", id));
		return ResponseEntity.noContent().build();
	}
	
}
