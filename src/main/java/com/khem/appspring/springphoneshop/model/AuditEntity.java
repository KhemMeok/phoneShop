package com.khem.appspring.springphoneshop.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Data;
@MappedSuperclass
@Data
public abstract class AuditEntity {

    @CreatedDate
    @Column(name = "date_created")
    private LocalDateTime dateCreate;

    @LastModifiedDate
    @Column(name = "date_updated")
    private LocalDateTime dataUpdate;
    
}
