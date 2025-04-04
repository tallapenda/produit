package com.testprod.produit.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testprod.produit.DTO.RoleDTO;
import com.testprod.produit.DTO.UsDTO;
import com.testprod.produit.entities.Role;
import com.testprod.produit.entities.User;


@Service
public interface UserService {
	
	User saveUser(User user);
	User finUserByUsername(String username);
	//User finUserByUsername2(String username);
	
	//RoleDTO addRole(RoleDTO role);
	Role addRole(Role role);
	
	User addRoleToUser(String username, String rolename);
	
	List<User> finAllUsers();
	//Optional<User> finUserByEmail(String email);
	User registerUser(User request);
	
	public void sendEmailUser(User u, String code);
	
	public User validateToken(String code);
	
    public User getUserById(Long id);

}

