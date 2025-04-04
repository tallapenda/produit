package com.testprod.produit.security;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testprod.produit.entities.User;
import com.testprod.produit.repository.UserRepository;
import com.testprod.produit.DTO.UsDTO;
import com.testprod.produit.Services.UserService;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userService;
	
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userService.finUserByUsername(username);
	
if (user==null) 
    throw new UsernameNotFoundException("Utilisateur introuvable !");
	
List<GrantedAuthority> auths = new ArrayList<>();
user.getRoles().forEach(role -> {
GrantedAuthority auhority = new
SimpleGrantedAuthority(role.getRole());
auths.add(auhority);
});

System.out.println("USERNAMMMMMMMMMMMMEEEEE "+ user.getUsername());
	System.out.println("PPPPPPPPPPASSSSSSSSSSSS "+ user.getPassword());
	System.out.println("ENABLEEEEEEEEEEEEEEEEEE "+ user.isEnabled());
	System.out.println("PPPPPPPPPPASSSSSSSSSSSS "+ auths);
	
return new org.springframework.security.core.
userdetails.User(user.getUsername(),user.getPassword(),auths);
	 

  }
}
