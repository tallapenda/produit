package com.testprod.produit.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JWTAuthorizationFilter extends OncePerRequestFilter{

	/*
	 * //Permet de filtrer le TOKEN
	 * 
	 * @Override protected void doFilterInternal(HttpServletRequest request,
	 * HttpServletResponse response, FilterChain filterChain) throws
	 * ServletException, IOException {
	 * 
	 * //Definition entête header response.setHeader("Acces-Control-Allow-Origin",
	 * "*"); response.setHeader("Acces-Control-Allow-Methods",
	 * "GET, HEAD, POST, DELETE,PUT, OPTIONS");
	 * response.setHeader("Acces-Control-Allow-Headers",
	 * "Acces-Control-Allow-Headers, Origin, Accept, " +
	 * "Content-Type, X-Requested-With, Acces-Control-Resquest-Method, " +
	 * "Acces-Control-Resquest-Headers, Authorization");
	 * response.setHeader("Acces-Control-Expose-Headers", "Authorization, " +
	 * "Acces-ControlAllow-Origin, Acces-ControlAllow-Origin");
	 * 
	 * if(request.getMethod().equals("OPTIONS")) {
	 * response.setStatus(HttpServletResponse.SC_OK);
	 * 
	 * return; }
	 * 
	 * //On recupere le token String jwt = request.getHeader("Authorization");
	 * 
	 * System.out.println("TALLLLLLLLLLLLLLLLKKKKKKKKKKKKKKK "+jwt);
	 * 
	 * if(jwt == null || !jwt.startsWith(SecParam.PREFIX)) {
	 * System.out.println("ETAAAAAAAAAP 1"); //On sort filterChain.doFilter(request,
	 * response); System.out.println("ETAAAAAAAAAP 2"); return; }
	 * 
	 * //VERIFICATION TOKEN EN PASSANT La clée SECRET ET LE TYPE ALGORITHM HMAC256
	 * JWTVerifier verifier =
	 * JWT.require(Algorithm.HMAC256(SecParam.SECRET)).build();
	 * 
	 * //ON retire le mot "Bearer" de la chaine token
	 * jwt.substring(SecParam.PREFIX.length());
	 * 
	 * //On decode la chaine token en enlevant le mot "Bearer " de la chaine de
	 * caractère DecodedJWT decoderJWT = verifier.verify(jwt.replaceAll("Bearer ",
	 * ""));
	 * 
	 * System.out.println("ETAAAAAAAAAP 444 :"+decoderJWT); //On extrait les infos
	 * Username, roles et token String username = decoderJWT.getSubject();
	 * 
	 * List<String> roles =
	 * decoderJWT.getClaims().get("roles").asList(String.class);
	 * 
	 * //On construit la chaine autorité pour y affecter les roles
	 * Collection<GrantedAuthority> authorities = new ArrayList<>();
	 * 
	 * //On parcours la chaine for(String r: roles) authorities.add(new
	 * SimpleGrantedAuthority(r));
	 * 
	 * //On transmet les infos à spring securité pour une mise à jour
	 * UsernamePasswordAuthenticationToken user = new
	 * UsernamePasswordAuthenticationToken(username, null, authorities);
	 * 
	 * //On dit a spring securité que l'authentification est bon
	 * SecurityContextHolder.getContext().setAuthentication(user);
	 * 
	 * //On passe au filter suivant filterChain.doFilter(request, response); }
	 */
	

		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		 FilterChain filterChain) throws ServletException, IOException {
			
		String jwt =request.getHeader("Authorization");
		System.out.println("TALLLLLLLLLLLLLLLLKKKKKKKKKKKKKKK "+jwt);
		if (jwt==null || !jwt.startsWith(SecParam.PREFIX))
		{
		filterChain.doFilter(request, response);
		 return;
		}
		
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParam.SECRET)).build();
		
				//enlever le préfixe Bearer du jwt
				jwt= jwt.substring(SecParam.PREFIX.length());
				
				DecodedJWT decodedJWT = verifier.verify(jwt);
				String username = decodedJWT.getSubject();
				
			List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
			Collection <GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			
				for(String r : roles)
				authorities.add(new SimpleGrantedAuthority(r));
				
				UsernamePasswordAuthenticationToken user =
						new
						UsernamePasswordAuthenticationToken(username,null,authorities);
				
						SecurityContextHolder.getContext().setAuthentication(user);
						
						filterChain.doFilter(request, response);
						}

}
