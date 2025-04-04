package com.testprod.produit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testprod.produit.DTO.ProduitDTO;
import com.testprod.produit.Services.ProduitService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/prod")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "API PRODUIT")
public class ProduitController {
	
	//injection de dependance
	@Autowired
	private ProduitService produitService;
	
	
	//liste de tous les produits
    @Operation(
      		description = "Affiche liste des produits",
      		summary = "Cette methode permet d'afficher la liste des produits",
      		responses = {
      				@ApiResponse(
      						description = "Liste Produits affichée",
      						responseCode = "200"),
      				@ApiResponse(
      						description = "Object Produit n'est pas valide",
      						responseCode = "401")
      		}
      		)
	@GetMapping("/allproduits")
	//@PreAuthorize("hasAnyAuthority('ADMIN')")
	public ResponseEntity<List<ProduitDTO>> getAllProduits(){
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
		return ResponseEntity.ok(produitService.getAllProduit());
	}
	
	
	//Trouver produit par ID
    @Operation(
    		description = "Rechercher un produit",
    		summary = "Cette methode permet de rechercher un produit",
    		responses = {
    				@ApiResponse(
    						description = "Object Produit trouvé",
    						responseCode = "200"),
    				@ApiResponse(
    						description = "Object Produit n'est pas valide",
    						responseCode = "401")
    		}
    		)
		@GetMapping("/parid/{id}")
		public ResponseEntity<ProduitDTO> getProduitsParId(@PathVariable("id") Long id){
			
			return ResponseEntity.ok(produitService.getProduit(id));
		}
	
    
		     //CREER NOUVEAU produit
        @Operation(
    		description = "Rechercher une produit",
    		summary = "Cette methode permet de creer un nouveau produit",
    		responses = {
    				 @ApiResponse(responseCode = "200", description = "Produit created successfully"),
    			     @ApiResponse(responseCode = "400", description = "Invalid input"),
    			     @ApiResponse(responseCode = "403", description = "Insufficient permissions")
    		}
    		)
			@PostMapping("/newproduit")
			public ResponseEntity<ProduitDTO> creerProduit(@RequestBody ProduitDTO produit){
					return new ResponseEntity<>(produitService.saveProduit(produit), HttpStatus.CREATED);
				}
				
				//Modifier produit
				@PutMapping("/updat")
				public ProduitDTO modifierProduit(@RequestBody ProduitDTO produit){
					
					return produitService.updateProduit(produit);
				}
				
				//Supprimer produit
				@DeleteMapping("/sup/{id}")
				public void deleteProduit(@PathVariable("id") Long id){ 
					
					produitService.deleteProduiById(id); 
				}
				
				//Afficher tout les produits de même catégorie
				@GetMapping("/prodParIdcat/{id}")
				public List<ProduitDTO> trouverProduitParCategorie(@PathVariable("id") Long id){
					 
					return produitService.finByCategorieID(id); 
				}
				
				//Rechercher produit par nom
				@GetMapping("/prodsByName/{nom}")
				public List<ProduitDTO> finByNomProduitContains(@PathVariable("nom") String nom){
					 
					return produitService.getProduitParNom(nom); 
				}

}
