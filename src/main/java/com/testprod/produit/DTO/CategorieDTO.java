package com.testprod.produit.DTO;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.testprod.produit.entities.Produit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategorieDTO {

	/*
	 * private Long id; private String nomCategorie; //@JsonIgnore private String
	 * desscription; //private Produit produit;
	 */
	
	    private Long idCat;
	    private String nomCat;
	    private String descriptionCat;
	    private List<ProduitDTO> produits;
	}
	

