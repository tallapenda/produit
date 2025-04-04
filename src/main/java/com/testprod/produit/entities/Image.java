package com.testprod.produit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Builder //Permet la création de objet complexe
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long idImage ;
	 private String name ;
	 private String type ;
	 @Column( name = "IMAGE" , length = 4048576 )
	 @Lob
	 private byte[] image;
	 @OneToOne
	 private Produit produit;

}
