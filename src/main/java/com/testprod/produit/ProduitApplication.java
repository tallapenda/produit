package com.testprod.produit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import com.testprod.produit.Services.UserService;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EntityScan("com.testprod.produit.entities")
public class ProduitApplication {

	@Autowired
	UserService userService;
	
	/*
	 * @Autowired BCryptPasswordEncoder bCryptPasswordEncoder;
	 */

	
	/*
	 * @PostConstruct void init_users() { //ajouter les rôles
	 * userService.addRole(new Role(null,"ADMIN")); userService.addRole(new
	 * Role(null,"USER"));
	 * 
	 * //ajouter les users
	 * 
	 * 
	 * userService.saveUser(new User(null,"Administrateur","nnnnnnn","Dev",
	 * "+9887777777", "admin@gmail.com", LocalDate.now(), 102222.0, "admin",
	 * bCryptPasswordEncoder.encode("admin"), true, null, null, null));
	 * 
	 * userService.saveUser(new User(null,"Talla","Informatique","dev",
	 * "+24443323222", "talladiopp@gmail.com", LocalDate.now(), 12000.0, "talla",
	 * bCryptPasswordEncoder.encode("talla123"), true, null, null, null));
	 * 
	 * //ajouter les rôles aux users userService.addRoleToUser("admin", "ADMIN");
	 * userService.addRoleToUser("talla", "USER"); }
	 * 
	 */

	
	public static void main(String[] args) {
		SpringApplication.run(ProduitApplication.class, args);
	}

}
