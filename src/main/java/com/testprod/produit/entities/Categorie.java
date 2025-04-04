package com.testprod.produit.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class Categorie implements Serializable {

	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 * 
	 * @Column(unique = true, nullable = false) //@NotBlank(message =
	 * "Categorie ID is required") // @Pattern(regexp = "^EMP\\d{4}$", message =
	 * "Categorie ID must be in format EMP followed by 4 digits") private Long id;
	 * 
	 * private String nomCategorie; private String desscription;
	 * 
	 * @OneToMany(mappedBy = "categorie") private List<Produit> produit;
	 */
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCat;
	private String nomCat;
	private String descriptionCat;
	
    @JsonIgnore
	@OneToMany(mappedBy = "categorie")
	private List<Produit> produits;
	
	
}
