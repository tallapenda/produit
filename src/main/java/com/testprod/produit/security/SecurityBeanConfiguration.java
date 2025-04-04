package com.testprod.produit.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityBeanConfiguration {

	//Pour cryptage pwd
	@Bean
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();
	}
	
	
	//Pour l'authentification
	
	@Bean
	public AuthenticationManager authentificationManager(AuthenticationConfiguration config) throws Exception 
	{
		return config.getAuthenticationManager();
		
	}
	
}


