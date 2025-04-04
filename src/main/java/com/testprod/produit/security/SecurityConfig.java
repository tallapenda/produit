package com.testprod.produit.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	

 	@Autowired
 	AuthenticationManager authMgr;
	
	
 	@Bean
 	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

 	 http.sessionManagement(session ->
 	session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

 	 .csrf(csrf -> csrf.disable())

 	.authorizeHttpRequests((requests)->requests
 			 .requestMatchers("/login").permitAll()
 			 .anyRequest().authenticated() )

 			 .addFilterBefore(new JWTAuthenticationFilter (authMgr),
 			 UsernamePasswordAuthenticationFilter.class);
 	 
 	     return http.build();
 	}

	


	 
	
	/*
	 * @Bean public SecurityFilterChain filterChain (HttpSecurity http) throws
	 * Exception { http.sessionManagement( session ->
	 * session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	 * 
	 * .csrf( csrf -> csrf.disable()) .cors(cors -> cors.configurationSource(new
	 * CorsConfigurationSource() {
	 * 
	 * @Override public CorsConfiguration getCorsConfiguration(HttpServletRequest
	 * request) {
	 * 
	 * CorsConfiguration cors = new CorsConfiguration();
	 * 
	 * cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
	 * cors.setAllowedMethods(Collections.singletonList("*"));
	 * cors.setAllowedHeaders(Collections.singletonList("*"));
	 * cors.setExposedHeaders(Collections.singletonList("Authorization")); return
	 * cors; } }))
	 * 
	 * 
	 * 
	 * .authorizeHttpRequests( requests -> requests
	 * 
	 * .requestMatchers("/api/prod/allproduits/**").hasAnyAuthority("ADMIN","USER")
	 * .requestMatchers(HttpMethod.GET,"/api/prod/parid/**").hasAnyAuthority("ADMIN"
	 * ,"USER")
	 * .requestMatchers(HttpMethod.POST,"/api/prod/newproduit/**").hasAnyAuthority(
	 * "ADMIN")
	 * .requestMatchers(HttpMethod.PUT,"/api/prod/updat/**").hasAuthority("ADMIN")
	 * .requestMatchers(HttpMethod.DELETE,"/api/prod/sup/**").hasAuthority("ADMIN")
	 * .anyRequest().authenticated() )
	 * 
	 * .addFilterBefore(new
	 * JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	 * 
	 * return http.build();
	 * 
	 * }
	 */
}
