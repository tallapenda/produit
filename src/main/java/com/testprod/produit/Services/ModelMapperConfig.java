package com.testprod.produit.Services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//ModelMapperConfig.java
	@Configuration
	@ComponentScan // Optional: Scans for annotated beans in the same package
	public class ModelMapperConfig {

	 @Bean
	 public ModelMapper modelMapper() {
	     return new ModelMapper();
	 }
	}
