package com.testprod.produit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.testprod.produit.entities.Categorie;
import com.testprod.produit.entities.Produit;



@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{
	
	//Recherche produit par nom
		@Query(value = "select * from Produit p where nomProduit like %:nom", nativeQuery = true)
		List<Produit> findByNomProduit(@Param("nom") String nom);

		 //Recherche par categorie
		@Query(value = "select * FROM Produit p WHERE p.nom_produit = like %?1 and p.prix_produit > ?2", nativeQuery = true)
		List<Produit> findByPrixProduit(String nom, double prix);
		
		 //Recherche tout les produits
			@Query(value = "select * FROM Produit ORDER BY id_produit DESC", nativeQuery = true)
			List<Produit> findAllProduit();
			
		
		
		@Query(value = "select * from Produit p where nom_produit like %:nom and prix_produit > :prix", nativeQuery = true)
		public List<Produit> findByNomProduitPrixProduit(@Param("nom") String nom, @Param("prix") Double prixProduit);
		
		//Requete en passant des entité comme parametre
		@Query(value = "select * from Produit p where p.categorie = ?1", nativeQuery = true)
		List<Produit> findByCategorie(Long id);
		
		//Rechercher produit par categorie
		List<Produit> findByCategorieIdCat(Long id);
		
		//Afficher les produits par ordre ASC
		@Query(value = "select * from Produit p order by nom_Produit ASC", nativeQuery = true)
			List<Produit> finProduits();
			
			//Trie liste produit par ordre
			@Query(value = "select * from Produit p order by prix_Produit DESC", nativeQuery = true)
			List<Produit> trierProduitsNomPrix();

}
