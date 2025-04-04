package com.testprod.produit.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Table(name = "users")
@Data 
@NoArgsConstructor 
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_Id;

    @NotBlank(message = "Full name is required")
    @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Full name contains invalid characters")
    private String fullName;

    @NotBlank(message = "Department is required")
    @Size(max = 50, message = "Department name cannot exceed 50 characters")
    private String department;

    @NotBlank(message = "Job title is required")
    @Size(max = 100, message = "Job title cannot exceed 100 characters")
    private String jobTitle;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number format")
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;

    //@NotNull(message = "Hire date is required")
    //@Past(message = "Hire date must be in the past")
    private LocalDate hireDate;

	/*
	 * @NotNull(message = "Employment status is required")
	 * 
	 * @Enumerated(EnumType.STRING) private EmploymentStatus employmentStatus;
	 */

    @Min(value = 0, message = "Salary cannot be negative")
    private Double salary;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    @JsonIgnore // 🔐 Empêche l'exposition du mot de passe dans les API
    private String password;
    
    private boolean enabled;

	/*
	 * @ManyToMany(fetch = FetchType.LAZY) // ⬅️ Lazy loading pour éviter des
	 * requêtes inutiles
	 * 
	 * @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_Id"),
	 * inverseJoinColumns = @JoinColumn(name = "role_id") ) private List<Role>
	 * roles;
	 */
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", 
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
    //private Set<Role> roles = new HashSet<>();
    private List<Role> roles;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

	/*
	 * @PrePersist
	 * 
	 * @PreUpdate private void cleanFields() { if (fullName != null) fullName =
	 * fullName.trim(); if (email != null) email = email.toLowerCase().trim(); }
	 */

	/*
	 * public enum EmploymentStatus { ACTIVE, INACTIVE, ON_LEAVE }
	 */
}
