package com.testprod.produit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.testprod.produit.DTO.ImageDTO;
import com.testprod.produit.entities.Image;
import com.testprod.produit.entities.Produit;


@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	

	 //Recherche par categorie
	@Query(value = "select * FROM image i WHERE i.produitId = ?1", nativeQuery = true)
	List<Image> findImageParProduit(Long idprod);

}
