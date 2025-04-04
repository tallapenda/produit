package com.testprod.produit.Services;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import com.testprod.produit.DTO.ImageDTO;
import com.testprod.produit.entities.Image;


@Service
public interface ImageService {
	
	/*
	 * ImageDTO uplaodImage(MultipartFile file) throws IOException;
	 * 
	 * ImageDTO getImageDetails(Long id) throws IOException;
	 * 
	 * ResponseEntity<byte[]> getImage(Long id) throws IOException;
	 * 
	 * void deleteImage(Long id) ;
	 * 
	 * ImageDTO uplaodImageProd(MultipartFile file, Long idProd) throws IOException;
	 * 
	 * List<ImageDTO> getImagesParProd(Long prodId);
	 */
 
 Image uplaodImage(MultipartFile file) throws IOException;
 Image getImageDetails(Long id) throws IOException;
 ResponseEntity<byte[]> getImage(Long id) throws IOException;
 void deleteImage(Long id) ;
 Image uplaodImageProd(MultipartFile file,Long idProd) throws IOException;
	List<Image> getImagesParProd(Long prodId);
 
}
