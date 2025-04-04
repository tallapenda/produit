package com.testprod.produit.Services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class WebConfig {
	@Bean 
	public WebMvcConfigurer corsConfigurer() {
		
		return new WebMvcConfigurer() { 
			
/*			@Override 
			public void addCorsMappings(CorsRegistry registry) 
			{ registry.addMapping("/**")
			// Autoriser tous les points de terminaison 
				.allowedOrigins("http://localhost:4200") 
				//.allowedOriginPatterns("*")
				// URL de l'application Angular 
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") 
				.allowedHeaders("*") 
				.allowCredentials(true); 
			// Si vous utilisez des cookies ou une authentification 
			} 
			};*/
			
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**") // Adjust the path as needed
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(false);
            }
        };
			
	} 
			}
	


