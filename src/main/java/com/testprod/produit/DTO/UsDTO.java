package com.testprod.produit.DTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.testprod.produit.entities.Role;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
//@Data
@Getter
@Setter
@Entity
@Builder
public class UsDTO {
    private Long user_Id;
    private String fullName;
    private String department;
    private String jobTitle;
    private String phoneNumber;
    private String email;
    private LocalDate hireDate;
    private Double salary;
    private String username;
    private String password;
    private boolean enabled;
    private List<Role> roles; 
    //private Set<RoleDTO> roles;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

		/*
		 * public enum EmploymentStatus { ACTIVE, INACTIVE, ON_LEAVE }
		 */

}
