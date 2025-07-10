package com.javaweb.api.entity;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity implements Serializable{
	
    private static final long serialVersionUID = -863164858986274318L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "createddate")
    @CreatedDate
    private Date creatddate ;
    
    @Column(name = "createdby")
    @CreatedBy
    private String createdby;
    
    @Column(name = "modifieddate")
    @LastModifiedDate
    private Date modifieddate;
    
    @Column(name = "modifiedby")
    @LastModifiedBy
    private String modifiedby;

	

}
