package com.khem.appspring.springphoneshop.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "color")
@Data
public class Color {
   @Id
   private Integer Id;
   private String name;
}
