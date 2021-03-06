package in.hortari.cta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.hortari.cta.entity.User;
import in.hortari.cta.service.UserService;

/**
 * This is a controller for handling/delegating requests to service layer.
 * 
 * @author SHUBHAM JAIN
 * @since 23-04-2018
 * 
 */
@RestController
@RequestMapping("/users")
public class UserController 
{	
	@Autowired
	private UserService userService;
	
	
	/**
	 * Used to create User
	 * 
	 * @param student
	 * @return
	 */
	@PostMapping("/signup")
	public ResponseEntity<User> createUser(@RequestBody User user) 
	{
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	/**
	 * Used to access an User
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/signin")
	public ResponseEntity<User> getUser(@RequestBody User user) 
	{
		return new ResponseEntity<User>(userService.getUser(user), HttpStatus.FOUND);
	}
	
	/**
	 * Used to update Currency 
	 * @param username
	 * @param favCoins
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@RequestParam("username") String username, @RequestParam("favCoins") ArrayList<String> favCoins) 
	{
		return new ResponseEntity<User>(userService.updateUser(username, favCoins), HttpStatus.OK);
	}
	
	/**
	 * API which verifies the user
	 * @param token
	 * @return
	 */
	@GetMapping("/verify")
	public ResponseEntity<User> authenticateUser(@RequestParam("token") String token) 
	{
		return new ResponseEntity<User>(userService.verifyUser(token), HttpStatus.ACCEPTED);
	}
}
