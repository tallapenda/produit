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

import com.testprod.produit.DTO.CategorieDTO;
import com.testprod.produit.Services.CategorieService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;



@RestController
@RequestMapping("/api/cat")
@CrossOrigin(origins = "*")
//@SecurityRequirement(name = "BearerAuth")
@Tag(name = "API CATEGORIE")
public class CategorieController {
	
	//injection de dependance
	@Autowired(required=true)
	private CategorieService categorieService;
	
	
	//CREER NOUVEAU categorie
    @Operation(
    		description = "Rechercher une catégorie",
    		summary = "Cette methode permet de rechercher une catégorie",
    		responses = {
    				@ApiResponse(
    						description = "Object Catégorie trouvé",
    						responseCode = "200"),
    				@ApiResponse(
    						description = "Object Catégorie n'est pas valide",
    						responseCode = "401")
    		}
    		)
	      @GetMapping("/catparid/{id}")
	      public CategorieDTO getCategorieParId(@PathVariable("id") Long id){
		
		     return categorieService.getCategorie(id);
	       }

	
	     //CREER NOUVEAU categorie
	      @Operation(
	      		description = "Enregistrer une catégorie",
	      		summary = "Cette methode permet de enregistrer/Modifier une catégorie",
	      		responses = {
	      				@ApiResponse(
	      						description = "Object Catégorie créer/Modifier",
	      						responseCode = "200"),
	      				@ApiResponse(
	      						description = "Object Catégorie n'est pas valide",
	      						responseCode = "401")
	      		}
	      		)
			@PostMapping(value = "/newcategorie")
			public ResponseEntity<CategorieDTO> creerCategorie(@RequestBody CategorieDTO categorieDTO){
				
				return new ResponseEntity<>(categorieService.saveCategorie(categorieDTO), HttpStatus.CREATED);
			}
			
			//liste de tous les categorie
	      @Operation(
	      		description = "Affiche liste des catégorie",
	      		summary = "Cette methode permet d'afficher la liste des catégories",
	      		responses = {
	      				@ApiResponse(
	      						description = "Liste Catégorie affichée",
	      						responseCode = "200"),
	      				@ApiResponse(
	      						description = "Object Catégorie n'est pas valide",
	      						responseCode = "401")
	      		}
	      		)
			@GetMapping("/allcategories")
			//@PreAuthorize("hasAnyAuthority('ADMIN')")
			public ResponseEntity<Flux<CategorieDTO>> getAllCategories(){
				
				return ResponseEntity.ok(categorieService.getAllCategorie());
			}
			
	      
			//Modifier categorie
	      @Operation(
	      		description = "Modifier une catégorie",
	      		summary = "Cette methode permet de Modifier une catégorie",
	      		responses = {
	      				@ApiResponse(
	      						description = "Object Catégorie Modifier",
	      						responseCode = "200"),
	      				@ApiResponse(
	      						description = "Object Catégorie n'est pas valide",
	      						responseCode = "401")
	      		}
	      		)
	      @PutMapping("/updat/{id}")
	      public ResponseEntity<CategorieDTO> modifierProduit(
	          @PathVariable("id") Long id, @RequestBody CategorieDTO categorieDTO) {
	    	
	    	  if (categorieDTO == null) {
	    	return ResponseEntity.badRequest().build();
	    	    }
	    	  
		return ResponseEntity.ok(categorieService.updateCategorie(id, categorieDTO));
			}
	      

			//Supprimer categorie
	         @Operation(
		      		description = "Supprimer une catégorie",
		      		summary = "Cette methode permet de supprimer une catégorie",
		      		responses = {
		      				@ApiResponse(
		      						description = "Object Catégorie supprimée",
		      						responseCode = "200"),
		      				@ApiResponse(
		      						description = "Object Catégorie n'est pas valide",
		      						responseCode = "401")
		      		}
		      		) 
			@DeleteMapping("/sup/{id}")
			public ResponseEntity<Void> deleteCategorie(@PathVariable("id") Long id){ 
				
				categorieService.deleteCategorieById(id); 
				
				return ResponseEntity.noContent().build();
			}
}
