package com.khem.appspring.springphoneshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Table(name = "models")
@Data
public class Model {
    @Id
    @GeneratedValue(generator = "models_seq_generator")
    @SequenceGenerator(name = "models_seq_generator", initialValue = 1, sequenceName = "models_seq_generator")
    
    private Long id;

    @Column(name = "models_column")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
    
    @NotNull(message = "{required.field}")
    @Column(name = "year_made")
    private Short yearMade;
}
