package com.testprod.produit.DTO;


import java.util.Date;
import java.util.List;

import com.testprod.produit.entities.Categorie;
import com.testprod.produit.entities.Image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProduitDTO {
	
	/*
	 * private Long id; private String nomProduit; private double prixProduit;
	 * private Date dateCreation; private Categorie categorie; private String
	 * nomCategorie;
	 * 
	 * private Image image;
	 * 
	 * //private List<Image> image;
	 * 
	 * private String imagePath;
	 */
	
	private Long id;
    private String nomProduit;
    private Double prixProduit;
    private Date dateCreation;
    private String imagePath;
    private Categorie categorie;
    //private Long idImage;
    private Image image;
    private List<Image> images;
}
