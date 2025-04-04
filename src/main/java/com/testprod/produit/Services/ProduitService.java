package com.testprod.produit.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.testprod.produit.DTO.ProduitDTO;
import com.testprod.produit.entities.Produit;

@Service
public interface ProduitService {
	
	ProduitDTO saveProduit(ProduitDTO p);
	ProduitDTO updateProduit(ProduitDTO p);
	
	ProduitDTO getProduit(Long id);
	List<ProduitDTO> getAllProduit();
	
	
	void deleteProduiById(Long id);
	void deleteProduit(Produit p);
	
	
	
	List<ProduitDTO> getProduitParNom(String nom);
	
	List<ProduitDTO> finProduitParCategorie(Long idcat);
	List<Produit> finByProduitNomPrix(String nom, double prix);
	List<ProduitDTO> finByCategorieID(Long id);
	
	List<Produit> finByOrderByNomProduitAsc();
	List<Produit> trierProduitNomPrix();
	
	ProduitDTO convertEntitesToDTO(Produit p);
	Produit convertDTOToEntites(ProduitDTO produitDTO);

}
