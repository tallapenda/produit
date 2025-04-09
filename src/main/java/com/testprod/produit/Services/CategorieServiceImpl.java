package com.testprod.produit.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.testprod.produit.DTO.CategorieDTO;
import com.testprod.produit.entities.Categorie;
import com.testprod.produit.repository.CategorieRepository;

import reactor.core.publisher.Flux;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;


@Component
public class CategorieServiceImpl implements CategorieService{
	
	
	//Injection de dependance categorie repository
	@Autowired
	 CategorieRepository categorieRepository;
	
	@Autowired
	AuditService auditService;
	
	//Injection pour construire et converture les entites en DTO
	@Autowired
	ModelMapper modelMapper;
	
	
	
	/*
	 * @Override public CategorieDTO convertEntitesToDTO(Categorie categorie) {
	 * 
	 * //Permet d'afficher les champs qui ne se figure pas dans l'entites categorie
	 * modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
	 * 
	 * CategorieDTO categorieDTO = modelMapper.map(categorie, CategorieDTO.class);
	 * 
	 * return categorieDTO;
	 * 
	 * }
	 * 
	 * 
	 * @Override public Categorie convertDTOToEntites(CategorieDTO categorieDTO) {
	 * 
	 * Categorie cat = new Categorie(); cat = modelMapper.map(categorieDTO,
	 * Categorie.class);
	 * 
	 * return cat;
	 * 
	 * }
	 * 
	 */
	
    public CategorieDTO convertEntitesToDTO(Categorie categorie) {
        if (categorie == null) {
            return null;
        }

        CategorieDTO dto = new CategorieDTO();
        dto.setIdCat(categorie.getIdCat());
        dto.setNomCat(categorie.getNomCat());
        dto.setDescriptionCat(categorie.getDescriptionCat());
        
        return dto;
    }

    public List<CategorieDTO> toDTOList(List<Categorie> categories) {
        return categories.stream()
                .map(this::convertEntitesToDTO)
                .collect(Collectors.toList());
    }

    public Categorie convertDTOToEntites(CategorieDTO dto) {
        if (dto == null) {
            return null;
        }

        Categorie categorie = new Categorie();
        categorie.setIdCat(dto.getIdCat());
        categorie.setNomCat(dto.getNomCat());
        categorie.setDescriptionCat(dto.getDescriptionCat());
        
        return categorie;
    }
	
	@Override
	public CategorieDTO saveCategorie(CategorieDTO p) {
		// TODO Auto-generated method stub
		return convertEntitesToDTO(categorieRepository.save(convertDTOToEntites(p)));
	}


	@Override
    //@PreAuthorize("hasAnyRole('HR', 'ADMIN', 'MANAGER')")
    @Transactional
	public CategorieDTO updateCategorie(Long categorieId, CategorieDTO p) {
		
		CategorieDTO existingCategorie = getCategorie(categorieId);
	        String oldValue = existingCategorie.toString();
	        
	        // Mise à jour infos categorie
	        existingCategorie.setNomCat(p.getNomCat());
	        existingCategorie.setDescriptionCat(p.getDescriptionCat());

	        CategorieDTO updatedCategorie = convertEntitesToDTO(categorieRepository.saveAndFlush(convertDTOToEntites(p)));
	        auditService.logAction("CATEGORIE", categorieId, "UPDATE", oldValue, updatedCategorie.toString());
		
		return updatedCategorie;
	}


	@Override
	public CategorieDTO getCategorie(Long id) {
		
		return convertEntitesToDTO(categorieRepository.findById(id).get());
	}


	@Override
	public Flux<CategorieDTO> getAllCategorie() {
		
		List<Categorie> categor = categorieRepository.findAllCategorie();
		
		List<CategorieDTO> catDTO = new ArrayList<>(categor.size());
		
		for(Categorie ct: categor) {
			
			catDTO.add(convertEntitesToDTO(ct));
		}
		
		return Flux.fromIterable(catDTO);
	}


	@Override
	public void deleteCategorieById(Long id) {
		
		categorieRepository.deleteById(id);
		
	}


	@Override
	public void deleteCategorie(Categorie p) {
		
		categorieRepository.delete(p);
		
	}

}
