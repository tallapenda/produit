package com.testprod.produit.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converters.Converter;
import org.modelmapper.ModelMapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.testprod.produit.DTO.ProduitDTO;
import com.testprod.produit.entities.Produit;
import com.testprod.produit.repository.ImageRepository;
import com.testprod.produit.repository.ProduitRepository;

import reactor.core.publisher.Flux;

import org.modelmapper.PropertyMap;


@Component
public class ProduitServiceImpl implements ProduitService{
	
	//Injection de dependance produit repository
	@Autowired
	 ProduitRepository produitrepository;
	
	//Injection pour construire et converture les entites en DTO
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ImageRepository imageRepository;
	

	
	@Override
	public ProduitDTO saveProduit(ProduitDTO p) {
		
		return convertEntitesToDTO(produitrepository.save(convertDTOToEntites(p)));
	}

	
	@Override
	public ProduitDTO updateProduit(ProduitDTO p) {
		System.out.println("ENTREEEEEEEEEEEEEEEEEEEEEEEEEEE");
  Long oldProdImageId = this.getProduit(p.getId()).getImage().getIdImage();
	
	Long newProdImageId = p.getImage().getIdImage();
	
	System.out.println("OLDE IMAG ID "+ oldProdImageId);
	System.out.println("new IMAG ID "+ newProdImageId);
	
	ProduitDTO prodUpdated = convertEntitesToDTO(produitrepository.save(convertDTOToEntites(p)));
	
	if (oldProdImageId != newProdImageId) //si l'image a été modifiée
		
		imageRepository.deleteById(oldProdImageId);
	
	//return prodUpdated;
	return prodUpdated;
	
	}

	
	@Override
	public void deleteProduiById(Long id) {
		ProduitDTO p = getProduit(id);
		 //suuprimer l'image avant de supprimer le produit
		try {
		Files.delete(Paths.get(System.getProperty("user.home")+"/imagesTest/"+p.getImagePath()));
		} catch (IOException e) {
		e.printStackTrace();
		} 
		
		produitrepository.deleteById(id);
		
	}

	@Override
	public void deleteProduit(Produit p) {
		produitrepository.delete(p);
		
	}

	@Override
	public ProduitDTO getProduit(Long id) {
		return convertEntitesToDTO(produitrepository.findById(id).get());
	}

	@Override
	public Flux<ProduitDTO> getAllProduit() {
		/*
		return produitrepository.findAll().stream().map(this::convertEntitesToDTO)
				.collect(Collectors.toList());
		*/
		//On recupere tous les produit
		List<Produit> prod = produitrepository.findAllProduit();
		
		//On converture en DTO
		List<ProduitDTO> prodDTO = new ArrayList<>(prod.size());
		
		for (Produit p : prod) {
			
			prodDTO.add(convertEntitesToDTO(p));	
		}
		
		return Flux.fromIterable(prodDTO);
		
	}
	
	@Override
	public List<ProduitDTO> getProduitParNom(String nom) {
		
		//On recupere tous les produit
				List<Produit> prodparnom = produitrepository.findByNomProduit(nom);
				
				//On converture en DTO
				List<ProduitDTO> prodDTO = new ArrayList<>(prodparnom.size());
				
				for (Produit p : prodparnom) {
					
					prodDTO.add(convertEntitesToDTO(p));
					
				}
				
				return prodDTO;
	}

	
	
	//Rechercher tout les produit de mêmecatégorie
	@Override
	public List<ProduitDTO> finProduitParCategorie(Long idcat) {
		
		//on recupere les produits par categorie		
        List<Produit> prod = produitrepository.findByCategorie(idcat);
		//ON CREE la liste DTO
		List<ProduitDTO> prodDTO = new ArrayList<>(prod.size());
		
		for (Produit p : prod) {
			
			prodDTO.add(convertEntitesToDTO(p));
			
		}
		
		return prodDTO;
	}

	
	@Override
	public List<Produit> finByProduitNomPrix(String nom, double prix) {
		return produitrepository.findByNomProduitPrixProduit(nom, prix);
	}


	//@Override
	//public List<Produit> finByCategorieIdCat(Long id) {
		//return produitrepository.findByCategorie(id);
	//}

	@Override
	public List<Produit> finByOrderByNomProduitAsc() {
		return produitrepository.finProduits();
	}

	@Override
	public List<Produit> trierProduitNomPrix() {
		return produitrepository.trierProduitsNomPrix();
	}

	@Override
	public List<ProduitDTO> finByCategorieID(Long id) {
		
		//on recupere les produits par categorie		
        List<Produit> prod = produitrepository.findByCategorieIdCat(id);
		//ON CREE la liste DTO
		List<ProduitDTO> prodDTO = new ArrayList<>(prod.size());
		
		for (Produit p : prod) {
			
			prodDTO.add(convertEntitesToDTO(p));
			
		}
		
		return prodDTO;
	}

	
	public ProduitDTO convertEntitesToDTO(Produit produit) {
        if (produit == null) {
            return null;
        }

        ProduitDTO dto = new ProduitDTO();
        dto.setId(produit.getIdProduit());
        dto.setNomProduit(produit.getNomProduit());
        dto.setPrixProduit(produit.getPrixProduit());
        dto.setDateCreation(produit.getDateCreation());
        dto.setImagePath(produit.getImagePath());
        dto.setImage(produit.getImage()); //Id de image du produit
        dto.setCategorie(produit.getCategorie());
        
        if (produit.getCategorie() != null) {
            dto.setCategorie(produit.getCategorie());
           // dto.setNomCategorie(produit.getCategorie().getNomCat());
        }
        
        return dto;
    }

    public List<ProduitDTO> toDTOList(List<Produit> produits) {
        return produits.stream()
                .map(this::convertEntitesToDTO)
                .collect(Collectors.toList());
    }

    public Produit convertDTOToEntites(ProduitDTO dto) {
        if (dto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setIdProduit(dto.getId());
        produit.setNomProduit(dto.getNomProduit());
        produit.setPrixProduit(dto.getPrixProduit());
        produit.setDateCreation(dto.getDateCreation());
        produit.setImagePath(dto.getImagePath());
        produit.setImage(dto.getImage());
        produit.setCategorie(dto.getCategorie());
        
        return produit;
    }
	
	
	/*
	 * @Override public ProduitDTO convertEntitesToDTO(Produit produit) {
	 * 
	 * //Permet d'afficher les champs qui ne se figure pas dans l'entites Produit
	 * modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	 * 
	 * ProduitDTO produitDTO = modelMapper.map(produit, ProduitDTO.class);
	 * 
	 * return produitDTO;
	 * 
	 * }
	 * 
	 * 
	 * @Override public Produit convertDTOToEntites(ProduitDTO produitDTO) {
	 * 
	 * Produit prod = new Produit(); prod = modelMapper.map(produitDTO,
	 * Produit.class);
	 * 
	 * return prod; }
	 */

}
