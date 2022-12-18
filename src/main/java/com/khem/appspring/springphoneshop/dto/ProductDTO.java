package com.khem.appspring.springphoneshop.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.khem.appspring.springphoneshop.model.Model;

public class ProductDTO {
    
    private Long id;

    
    private String name;
 
    private Model model;

  
    private Short yearMade;
 
    private String color;
    
   
    private BigDecimal importPrice;

 
    private Double salePrice;

 
    private LocalDate dateImport;


  
    private String imagePath;
}
