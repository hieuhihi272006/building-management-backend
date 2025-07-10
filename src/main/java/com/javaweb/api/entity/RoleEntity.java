package com.javaweb.api.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Table(name="role")
@Entity
@Getter
@Setter
public class RoleEntity extends BaseEntity{
    private static final long serialVersionUID = -6525302831793188081L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name ;
	
	@Column(name="code")
	private String code;
	
	public static long getSerialVersionUID() {
        return serialVersionUID;
    }
	
	@ManyToMany(mappedBy="roles" , fetch = FetchType.LAZY)
	private List<UserEntity> user = new ArrayList<>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
