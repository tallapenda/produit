package com.testprod.produit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testprod.produit.DTO.UsDTO;
import com.testprod.produit.entities.User;
import com.testprod.produit.repository.UserRepository;
import com.testprod.produit.Services.UserService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	
	//recherche tout les users
	@GetMapping("all")
	public List<User> getAllUsers(){
		return userService.finAllUsers();
	}
	
	//Enregistrement User
	@PostMapping("register")
	public User register(@RequestBody User request) {
		
		return userService.registerUser(request);
	}
	
	
	@GetMapping("/verifyEmail/{token}")
	 public User verifyEmail(@PathVariable("token") String token){ 
		//System.out.println("cooooooode "+ token);
	return userService.validateToken(token);
	 }

}

