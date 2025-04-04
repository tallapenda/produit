package com.testprod.produit.DTO;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO {
    
    private Long role_id;
    
    @NotBlank(message = "Role name is required")
    private String role;
    
}


