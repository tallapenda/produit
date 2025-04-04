package com.testprod.produit.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.testprod.produit.email.EmailSender;
import com.testprod.produit.DTO.RoleDTO;
import com.testprod.produit.DTO.UsDTO;
import com.testprod.produit.entities.Role;
import com.testprod.produit.entities.User;
import com.testprod.produit.entities.VerificationToken;
import com.testprod.produit.exeption.EmailAlreadyExistsException;
import com.testprod.produit.exeption.ExpiredTokenException;
import com.testprod.produit.exeption.InvalidTokenException;
import com.testprod.produit.repository.RoleRepository;
import com.testprod.produit.repository.UserRepository;
//import com.talla.demo.service.register.RegistrationRequest;
import com.testprod.produit.repository.VerificationTokenRepository;

import jakarta.persistence.EntityNotFoundException;


//@Transactional
@Component
public class UserServiceImpl implements UserService {
    @Autowired
	UserRepository userRepository;
	
    @Autowired
	RoleRepository roleRepository;
    
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    
    @Autowired
    EmailSender emailSender;
    
	
	@Override
	public User saveUser(User user) {
		
		//Cryptage pwd
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		//return convertToDTO(userRepository.save(convertToEntites(user)));
		
		return userRepository.save(user);
	}

	@Override
	public User finUserByUsername(String username) {
		//return convertToDTO(userRepository.findByUsername(username));
		return userRepository.findByUsername(username);
	}

	/*
	 * @Override public RoleDTO addRole(RoleDTO role) {
	 * System.out.println("kkkkkkkkkkkkkkkkkkk "+role.getRole()); return
	 * convertToRoleDTO(roleRepository.save(RoletoEntity(role))); }
	 */
	
	@Override
	public Role addRole(Role role) {
		return roleRepository.save(role);
		}

	@Override
	public User addRoleToUser(String username, String rolename) {
		
		
		  User us = userRepository.findByUsername(username); 
		  Role role = roleRepository.findByRole(rolename);
		  
		  us.getRoles().add(role);
		  
		  //userRepository.save(convertToEntites(us));
		  userRepository.save(us);
		 
		
		/*
		 * UsDTO us = userRepository.findByUsername(username); if (us == null) { throw
		 * new UsernameNotFoundException("User not found: " + username); }
		 * 
		 * Role roleOptional = roleRepository.findByRole(rolename);
		 * 
		 * List<Role> rol = new ArrayList<>(); if (roleOptional !=null) {
		 * 
		 * rol.add(roleOptional);
		 * 
		 * } else { throw new IllegalArgumentException("Role not found: " + rolename); }
		 * 
		 * //ajour dans le USER us.setRoles(rol);
		 * 
		 * // Save the user entity if necessary
		 * userRepository.save(convertToEntites(us));
		 */
		
		//return convertToDTO(us);
		return us;
	}

	@Override
	public List<User> finAllUsers() {
		//return toDTOList(userRepository.findAll());
		return userRepository.findAll();
	}

	@Override
	public User registerUser(User newUser) {
		
		System.out.println("ENTREEEEEEEEEEEEEE ");
		//On recherche si l'email du User existe
		Optional<User> optionaluser = userRepository.findByEmail(newUser.getEmail());
		
		if(optionaluser.isPresent())
			//On sort en appelant la methode EmailAlreadyExistsException
			throw new EmailAlreadyExistsException("email déjà existant!");
		
		   
	  //Premiere etape sauve User
	  //UsDTO utilisateur = convertToDTO(userRepository.save(convertToEntites(newUser)));
	  User utilisateur = userRepository.save(newUser);
		   
		 //Recherche Role User
			/*
			 * Optional<Role> roleOptional = roleRepository.findByName("USER");
			 * 
			 * if (roleOptional.isPresent()) { //ajour dans le USER
			 * newUser.getRoles().add(roleOptional.get()); // Extract Role from Optional }
			 * else { throw new IllegalArgumentException("Role not found: " + "USER"); }
			 */
		
		  Role r = roleRepository.findByRole("USER"); 
		   List<Role> role = new ArrayList<>();
		   //ajout role
		   role.add(r);
		   
		   //ajour dans le USER
		   newUser.setRoles(role);
		   
		   //ajour dans le USER
		   //newUser.setRoles(roleOptional);
		System.out.println("ROLLELLLLLLLLLLLL NAMMMMME "+ newUser.getRoles());
		   
		 //génére le code secret 
		   String code = this.generateCode();
		   User usd = userRepository.getById(utilisateur.getUser_Id());
		   
		   VerificationToken token = new VerificationToken(code, usd);
		   //Enregistrement Token
		   verificationTokenRepository.save(token); 
		   
		   //Envoi de l'email au USER
		   sendEmailUser(newUser, token.getToken());
		   
		 //Deuxieme etape sauve User
		  // return convertToDTO(userRepository.save(convertToEntites(newUser)));   
		   
		   return newUser;
		   
		 
	}
	
        //Generation TOKEN
	    public String generateCode() {
		   Random random = new Random();
		   Integer code = 100000 + random.nextInt(900000); 
		   
		   return code.toString();
		  }
	    
	    //Envoi mail
	    @Override
	    public void sendEmailUser(User u, String code) {
	    	
	    	 String emailBody ="Bonjour "+ "<h1>"+u.getUsername() +"</h1>" +
	    	 " Votre code de validation est "+"<h1>"+code+"</h1>"; 
	    	 
	    	emailSender.sendEmail(u.getEmail(), emailBody);
	    	
	    	}
	    
	    
	    //Methode vérification validité du token
	    @Override
	    public User validateToken(String code) {
	    	System.out.println("COOOOOOOOOOOOODE "+ code);
	    VerificationToken token = verificationTokenRepository.findByToken(code);
	    System.out.println("TOOOOKENNNNNNN "+ token);
	    
	    System.out.println(token.getUser().getFullName());
	    System.out.println(token.getUser().getEmail());
	    System.out.println(token.getUser().getUser_Id());
	    System.out.println(token.getUser().getRoles());
	    
	     if(token == null){
	     throw new InvalidTokenException("Invalid Token");
	     }
	     
	     //On recupere le User qui appartient au token
	     User user = token.getUser();
	     
	     Calendar calendar = Calendar.getInstance();
	     
	     //On teste si le token n'est pas expiré ?
	    if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
	    	
	    verificationTokenRepository.delete(token);
	    	
	     throw new ExpiredTokenException("expired Token");
	     }
	    
	    // user.setEnabled(true);
	    convertToDTO(userRepository.save(user));
	    
	     return user;
	    }
	    
	    
	    public UsDTO convertToDTO(User user) {
	        if (user == null) {
	            return null;
	        }

	        UsDTO dto = new UsDTO();

	        dto.setUser_Id(user.getUser_Id());
	        dto.setFullName(user.getFullName());
	        dto.setJobTitle(user.getJobTitle());
	        dto.setEmail(user.getEmail());
	        dto.setSalary(user.getSalary());
	        dto.setHireDate(LocalDate.now().minusDays(1));
	        dto.setEnabled(true);

	        // Populate roles from User to UsDTO
	        if (user.getRoles() != null) {
	            dto.setRoles(new ArrayList<>(user.getRoles())); // Directly copy the list of Role entities
	        } else {
	            dto.setRoles(new ArrayList<>()); // Set an empty list if user.getRoles() is null
	        }

	        dto.setUsername(user.getUsername());
	        dto.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        dto.setCreatedAt(user.getCreatedAt());
	        dto.setUpdatedAt(user.getUpdatedAt());

	        return dto;
	    }
	
	    
	    
	    public User convertToEntites(UsDTO dto) {
	    	
	        User user = new User();
	        
	        user.setUser_Id(dto.getUser_Id());
	        user.setFullName(dto.getFullName());
	        user.setDepartment(dto.getDepartment());
	        user.setJobTitle(dto.getJobTitle());
	        user.setPhoneNumber(dto.getPhoneNumber());
	        user.setEmail(dto.getEmail());
	        user.setHireDate(LocalDate.now().minusDays(1));
	       // user.setEmploymentStatus(User.EmploymentStatus.valueOf(dto.getEmploymentStatus().name()));
	        user.setSalary(dto.getSalary());
	        user.setUsername(dto.getUsername());
	        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
	        user.setEnabled(true);
	        user.setRoles(dto.getRoles());
	        
	        return user;
	    }
	    
	    
	    private RoleDTO convertToRoleDTO(Role role) {
	        RoleDTO dto = new RoleDTO();
	        dto.setRole_id(role.getRole_id());
	        dto.setRole(role.getRole());
	        
	        return dto;
	    }
	    
	    public static Role RoletoEntity(RoleDTO dto) {
	        Role role = new Role();
	        role.setRole_id(dto.getRole_id());
	        role.setRole(dto.getRole());
	        return role;
	    }

	    public List<UsDTO> toDTOList(List<User> users) {
	        return users.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

		@Override
		 @Transactional(readOnly = true)
	    public User getUserById(Long id) {
	        User user = userRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	        return user;
	    }

}

