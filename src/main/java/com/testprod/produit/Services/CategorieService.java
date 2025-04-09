package com.testprod.produit.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testprod.produit.DTO.CategorieDTO;
import com.testprod.produit.entities.Categorie;

import reactor.core.publisher.Flux;


@Service
public interface CategorieService {
	
	CategorieDTO saveCategorie(CategorieDTO p);
	CategorieDTO updateCategorie(Long id, CategorieDTO p);
	
	CategorieDTO getCategorie(Long id);
	Flux<CategorieDTO> getAllCategorie();
	
	
	void deleteCategorieById(Long id);
	void deleteCategorie(Categorie p);
	
	CategorieDTO convertEntitesToDTO(Categorie p);
	Categorie convertDTOToEntites(CategorieDTO categorieDTO);
	

}
