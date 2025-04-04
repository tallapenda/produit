package com.testprod.produit.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.testprod.produit.DTO.ImageDTO;
import com.testprod.produit.DTO.ProduitDTO;
import com.testprod.produit.entities.Image;
import com.testprod.produit.entities.Produit;
import com.testprod.produit.repository.ImageRepository;
import com.testprod.produit.repository.ProduitRepository;

@Service
public class ImageServiceImpl implements ImageService {

	/*
	 * @Autowired ImageRepository imageRepository;
	 * 
	 * @Autowired ProduitService produitService;
	 * 
	 * @Autowired ProduitRepository produitRepository;
	 * 
	 * 
	 * public ImageDTO convertEntitesToDTO(Image image) { if (image == null) {
	 * return null; }
	 * 
	 * ImageDTO dto = new ImageDTO(); dto.setId(image.getId());
	 * dto.setNomImage(image.getNomImage()); dto.setType(image.getType());
	 * dto.setImage(image.getImage());
	 * 
	 * if (image.getProduit() != null) {
	 * dto.setProduitId(image.getProduit().getId()); }
	 * 
	 * return dto; }
	 * 
	 * public List<ImageDTO> toDTOList(List<Image> images) { return images.stream()
	 * .map(this::convertEntitesToDTO) .collect(Collectors.toList()); }
	 * 
	 * public Image convertDTOToEntites(ImageDTO dto) { if (dto == null) { return
	 * null; }
	 * 
	 * Image image = new Image(); image.setId(dto.getId());
	 * image.setNomImage(dto.getNomImage()); image.setType(dto.getType());
	 * image.setImage(dto.getImage());
	 * 
	 * return image; }
	 * 
	 * @Override public ImageDTO uplaodImage(MultipartFile file) throws IOException
	 * { Ce code commenté est équivalent au code utilisant le design pattern Builder
	 * Image image = new Image(null, file.getOriginalFilename(),
	 * file.getContentType(), file.getBytes(), null); return
	 * imageRepository.save(image);
	 * 
	 * return convertEntitesToDTO(imageRepository.save(Image.builder()
	 * .nomImage(file.getOriginalFilename()) .Type(file.getContentType())
	 * .image(file.getBytes()).build()) ); }
	 * 
	 * @Override public ImageDTO getImageDetails(Long id) throws IOException{
	 * 
	 * final Optional<Image> dbImage = imageRepository.findById(id);
	 * 
	 * return convertEntitesToDTO(Image.builder() .id(dbImage.get().getId())
	 * .nomImage(dbImage.get().getNomImage()) .Type(dbImage.get().getType())
	 * .image(dbImage.get().getImage()).build()) ; }
	 * 
	 * @Override public ResponseEntity<byte[]> getImage(Long id) throws IOException{
	 * 
	 * final Optional<Image> dbImage = imageRepository.findById(id);
	 * 
	 * return
	 * ResponseEntity.ok().contentType(MediaType.valueOf(dbImage.get().getType()))
	 * .body(dbImage.get().getImage()); }
	 * 
	 * @Override public void deleteImage(Long id) {
	 * 
	 * imageRepository.deleteById(id);
	 * 
	 * }
	 * 
	 * 
	 * @Override public ImageDTO uplaodImageProd(MultipartFile file,Long idProd)
	 * throws IOException {
	 * 
	 * Produit p = new Produit(); p.setId(idProd);
	 * 
	 * return convertEntitesToDTO(imageRepository.save(Image.builder()
	 * .nomImage(file.getOriginalFilename()) .Type(file.getContentType())
	 * .image(file.getBytes()) .produit(p).build()) ); }
	 * 
	 * 
	 * @Override public List<ImageDTO> getImagesParProd(Long prodId) {
	 * 
	 * List<Image> imag = imageRepository.findImageParProduit(prodId);
	 * 
	 * List<ImageDTO> imgDTO = new ArrayList<>(imag.size());
	 * 
	 * for (Image mg : imag) {
	 * 
	 * imgDTO.add(convertEntitesToDTO(mg));
	 * 
	 * }
	 * 
	 * return imgDTO; }
	 */

	@Autowired
	 ImageRepository imageRepository;
	
	 @Autowired
	 ProduitRepository produitRepository;

	 @Autowired
	 ProduitService produitService;
	 @Override
	 public Image uplaodImage(MultipartFile file) throws IOException {
	 /*Ce code commenté est équivalent au code utilisant le design pattern Builder
	 * Image image = new Image(null, file.getOriginalFilename(),
	 file.getContentType(), file.getBytes(), null);
	 return imageRepository.save(image);*/
	 return imageRepository.save(Image.builder()
	 .name(file.getOriginalFilename())
	 .type(file.getContentType())
	 .image(file.getBytes()).build() );
	 }
	 
	 @Override
	 public Image getImageDetails(Long id) throws IOException{
	 final Optional<Image> dbImage = imageRepository. findById (id);
	 return Image.builder()
	 .idImage(dbImage.get().getIdImage())
	 .name(dbImage.get().getName())
	 .type(dbImage.get().getType())
	 .image(dbImage.get().getImage()).build() ;
	 }
	 @Override
	 public ResponseEntity<byte[]> getImage(Long id) throws IOException{
	 final Optional<Image> dbImage = imageRepository. findById (id);
	 return ResponseEntity
	 .ok()
	 .contentType(MediaType.valueOf(dbImage.get().getType()))
	 .body(dbImage.get().getImage());
	 }

	 @Override
	 public void deleteImage(Long id) {
	 imageRepository.deleteById(id);
	 }
	 
	 
	 @Override
		public List<Image> getImagesParProd(Long prodId) {
			Produit p = produitRepository.findById(prodId).get();
				return p.getImages();
		}
	 
	 @Override
		public Image uplaodImageProd(MultipartFile file,Long idProd) 
		throws IOException {
				Produit p = new Produit();
				p.setIdProduit(idProd);
		return imageRepository.save(Image.builder()
		 .name(file.getOriginalFilename())
		 .type(file.getContentType())
		 .image(file.getBytes())
		 .produit(p).build() );
		}
}
