package com.testprod.produit.Services;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testprod.produit.DTO.RoleDTO;
import com.testprod.produit.entities.Role;
import com.testprod.produit.repository.RoleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {
    
    private final RoleRepository roleRepository;
    
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
        return convertToDTO(role);
    }
    
    @Transactional(readOnly = true)
    public Role getRoleByName(String name) {
        Role role = roleRepository.findByRole(name);
               
        
        return convertToDTO(role);
    }
    
    @Transactional
    public Role createRole(Role roleDTO) {
        if (roleRepository.existsByRole(roleDTO.getRole())) {
            throw new IllegalArgumentException("Role name already exists");
        }
        
        Role role = new Role();
        role.setRole(roleDTO.getRole());
      
        
        Role savedRole = roleRepository.save(role);
        return convertToDTO(savedRole);
    }
    
    @Transactional
    public Role updateRole(Long id, Role roleDTO) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with id: " + id));
        
        // Check if name is being changed and if it's already taken
        if (!role.getRole().equals(roleDTO.getRole()) && 
            roleRepository.existsByRole(roleDTO.getRole())) {
            throw new IllegalArgumentException("Role name already exists");
        }
        
        role.setRole(roleDTO.getRole());
       
        
        Role updatedRole = roleRepository.save(role);
        return convertToDTO(updatedRole);
    }
    
    @Transactional
    public void deleteRole(Long id) {
        if (!roleRepository.existsById(id)) {
            throw new EntityNotFoundException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }
    
    private Role convertToDTO(Role role) {
        Role dto = new Role();
        dto.setRole_id(role.getRole_id());
        dto.setRole(role.getRole());
        return dto;
    }
}  
