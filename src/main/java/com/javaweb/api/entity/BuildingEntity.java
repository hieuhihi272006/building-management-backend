package com.javaweb.api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Table(name = "building")
@Getter
@Setter
public class BuildingEntity extends BaseEntity{
	
	@Column(name = "name")
	private String name ;
	
	@Column(name="street")
	private String street ;
	
	@Column(name = "ward")
	private String ward ;
	
	@Column(name = "district")
	private String district ;
	
	@Column(name="structure")
    private String structure;

    @Column(name="numberofbasement")
    private Long numberOfBasement;

    @Column(name="floorarea")
    private Long floorArea;

    @Column(name="direction")
    private String direction;

    @Column(name="level")
    private String level;

    @Column(name="rentprice")
    private Long rentPrice;

    @Column(name="rentpricedescription")
    private String rentPriceDes;

    @Column(name="servicefee")
    private String serviceFee;

    @Column(name="carfee")
    private String carFee;

    @Column(name="motofee")
    private String motoFee;

    @Column(name="overtimefee")
    private String overtimeFee;

    @Column(name="waterfee")
    private String waterFee;

    @Column(name="deposit")
    private String deposit;

    @Column(name="payment")
    private String payment;

    @Column(name="renttime")
    private String rentTime;

    @Column(name="brokeragefee")
    private Long brokerageFee;

    @Column(name="type")
    private String typeCode;

    @Column(name="note")
    private String note ;

    @Column(name="linkofbuilding")
    private String link ;

    @Column(name="map")
    private String map;

    @Column(name="avatar")
    private String avatar;

    @Column(name="managername")
    private String managerName;

    @Column(name="managerphone")
    private String managerPhone;
    
    
    @OneToMany(mappedBy = "building" , fetch = FetchType.LAZY ,cascade = {CascadeType.PERSIST , CascadeType.MERGE} , orphanRemoval = true)
    private List<RentEntity> rentEntities = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "assignmentbuilding" ,
               joinColumns = @JoinColumn(name="buildingid" , nullable = false),
               inverseJoinColumns = @JoinColumn(name="staffid",nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

	
}
