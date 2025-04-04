package com.testprod.produit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.testprod.produit.entities.Categorie;



@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
	 //Recherche tout les produits
	@Query(value = "select * FROM categorie ORDER BY id_cat DESC", nativeQuery = true)
	List<Categorie> findAllCategorie();

}
