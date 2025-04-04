package com.testprod.produit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testprod.produit.entities.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	//Optional<Role> findByRoles(String name);
    
    Boolean existsByRole(String name);
    
  //Recherche User par Role
  	Role findByRole(String name);
}
