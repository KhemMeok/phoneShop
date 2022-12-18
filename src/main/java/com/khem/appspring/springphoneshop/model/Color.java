package com.khem.appspring.springphoneshop.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "color")
@Data
public class Color {
   private Integer Id;
   private String name;
}
