package com.testprod.produit.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {
    private Long id;
    private String nomImage;
    private String type;
    private byte[] image;
    private Long produitId;
}
