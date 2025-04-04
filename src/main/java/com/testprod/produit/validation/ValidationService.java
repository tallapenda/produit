package com.testprod.produit.validation;





import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.testprod.produit.entities.Categorie;
import com.testprod.produit.repository.CategorieRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ValidationService {
    
    private final CategorieRepository categorieRepository;
    private final Validator validator;
    
    
    public void validateCategorie(Categorie categorie) {
        // Perform Jakarta Validation
        Set<ConstraintViolation<Categorie>> violations = validator.validate(categorie);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        
        
        // Check for duplicate categorie ID
        if (categorie.getIdCat() == null) { 
        	// New categorie
            if (categorieRepository.findById(categorie.getIdCat()) != null) {
                throw new ValidationException("Catégorie ID already exists");
            }
        } else { 
        	// Existing employee
            Optional<Categorie> existing = categorieRepository.findById(categorie.getIdCat());
            
            if (existing != null && !existing.get().equals(categorie.getIdCat())) {
                throw new ValidationException("Categorie ID already exists");
            }
        }
        
		/*
		 * // Check for duplicate email if (categorieRepository.existsByEmailAndIdNot(
		 * categorie.getEmail(), categorie.getId() != null ? categorie.getId() : -1L)) {
		 * throw new ValidationException("Email already exists"); }
		 * 
		 * // Check for duplicate national ID if provided if (employee.getNationalId()
		 * != null && !employee.getNationalId().isEmpty()) { if
		 * (categorieRepository.existsByNationalIdAndIdNot( categorie.getNationalId(),
		 * categorie.getId() != null ? categorie.getId() : -1L)) { throw new
		 * ValidationException("National ID already exists"); } }
		 */
    }
    
    public String formatValidationErrors(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
            .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
            .collect(Collectors.joining("\n"));
    }
}
