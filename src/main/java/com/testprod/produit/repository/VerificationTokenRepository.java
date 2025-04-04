package com.testprod.produit.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.testprod.produit.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>{
	
	VerificationToken findByToken(String token);

}
