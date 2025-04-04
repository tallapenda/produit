package com.testprod.produit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.testprod.produit.DTO.ImageDTO;
import com.testprod.produit.DTO.ProduitDTO;
import com.testprod.produit.entities.Image;
import com.testprod.produit.entities.Produit;
import com.testprod.produit.Services.ImageService;
import com.testprod.produit.Services.ProduitService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RestController
@RequestMapping("/api/image")
@CrossOrigin(origins = "*")
public class ImageRestController {
	
	/*
	 * @Autowired ImageService imageService;
	 * 
	 * @Autowired ProduitService produitService;
	 * 
	 * @RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
	 * public void uploadImageFS(@RequestParam("image") MultipartFile
	 * file, @PathVariable("id") Long id) throws IOException {
	 * 
	 * ProduitDTO p = produitService.getProduit(id); p.setImagePath(id+".jpg");
	 * 
	 * Files.write(Paths.get(System.getProperty("user.home")+"/imagesTest/"+p.
	 * getImagePath()) , file.getBytes()); produitService.saveProduit(p);
	 * 
	 * }
	 * 
	 * //Affichage Image
	 * 
	 * @RequestMapping(value = "/loadfromFS/{id}" , method = RequestMethod.GET,
	 * produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE) public byte[]
	 * getImageFS(@PathVariable("id") Long id) throws IOException {
	 * 
	 * ProduitDTO p = produitService.getProduit(id); return
	 * Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/imagesTest/"+p
	 * .getImagePath())); }
	 * 
	 * //Enregistrer Image
	 * 
	 * @RequestMapping(value = "/upload", method = RequestMethod.POST) public
	 * ImageDTO uploadImage(@RequestParam("image") MultipartFile file) throws
	 * IOException { return imageService.uplaodImage(file); }
	 * 
	 * @RequestMapping(value = "/get/info/{id}", method = RequestMethod.GET) public
	 * ImageDTO getImageDetails(@PathVariable("id") Long id) throws IOException {
	 * return imageService.getImageDetails(id); }
	 * 
	 * @RequestMapping(value = "/load/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws
	 * IOException { return imageService.getImage(id); }
	 * 
	 * @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE) public
	 * void deleteImage(@PathVariable("id") Long id) { imageService.deleteImage(id);
	 * }
	 * 
	 * @RequestMapping(value = "/update", method = RequestMethod.PUT) public
	 * ImageDTO UpdateImage(@RequestParam("image") MultipartFile file) throws
	 * IOException { return imageService.uplaodImage(file); }
	 * 
	 * @PostMapping(value = "/uplaodImageProd/{idProd}" ) public ImageDTO
	 * uploadMultiImages(@RequestParam("image") MultipartFile file,
	 * 
	 * @PathVariable("idProd") Long idProd) throws IOException {
	 * 
	 * return imageService.uplaodImageProd(file, idProd);
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/getImagesProd/{idProd}", method =
	 * RequestMethod.GET) public List<ImageDTO>
	 * getImagesProd(@PathVariable("idProd") Long idProd) throws IOException {
	 * return imageService.getImagesParProd(idProd); }
	 */
	
	 @Autowired
	 ImageService imageService ;
	 
	 @Autowired
	 ProduitService produitService;
	 
	 
	 @RequestMapping(value = "/uploadFS/{id}" , method = RequestMethod.POST)
		public void uploadImageFS(@RequestParam("image") MultipartFile 
				file, @PathVariable("id") Long id) throws IOException {
			
			ProduitDTO p = produitService.getProduit(id); 
			p.setImagePath(id+".jpg");

			Files.write(Paths.get(System.getProperty("user.home")+"/imagesTest/"+p.getImagePath())
					, file.getBytes());
			produitService.saveProduit(p);

		}
	 
	 @RequestMapping(value = "/loadfromFS/{id}" , method = RequestMethod.GET,
				produces = org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
		public byte[] getImageFS(@PathVariable("id") Long id) throws IOException {

			ProduitDTO p = produitService.getProduit(id);
		return
	 Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/imagesTest/"+p.getImagePath()));
		}

	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/upload" , method = RequestMethod.POST)
	 public Image uploadImage(@RequestParam("image")MultipartFile file) throws IOException {
		 	return imageService.uplaodImage(file);
	 }
	 
	 @PostMapping(value = "/uplaodImageProd/{idProd}" )
	 public Image uploadMultiImages(@RequestParam("image")MultipartFile file,
			 @PathVariable("idProd") Long idProd) 
					 throws IOException {
		 return imageService.uplaodImageProd(file,idProd);
	 }
	 
	 @RequestMapping(value = "/getImagesProd/{idProd}" , method = RequestMethod.GET)
	 public List<Image> getImagesProd(@PathVariable("idProd") Long idProd) 
			 throws IOException {
		 return imageService.getImagesParProd(idProd);
	 }
	 
	 
	 
	 
	 @RequestMapping(value = "/get/info/{id}" , method = RequestMethod.GET)
	 public Image getImageDetails(@PathVariable("id") Long id) throws IOException {
	 return imageService.getImageDetails(id) ;
	 }
	 
	 
	 @RequestMapping(value = "/load/{id}" , method = RequestMethod.GET)
	 public ResponseEntity<byte[]> getImage(@PathVariable("id") Long id) throws IOException 
	{
	 return imageService.getImage(id);
	 }
	 
	 
	 @RequestMapping(value = "/delete/{id}" , method = RequestMethod.DELETE)
	 public void deleteImage(@PathVariable("id") Long id){
	 imageService.deleteImage(id);
	 }
	 
	 
	
	 @RequestMapping(value="/update",method = RequestMethod.PUT)
	 public Image UpdateImage(@RequestParam("image")MultipartFile file) throws IOException {
	 return imageService.uplaodImage(file);
	 }
}
