package com.javaweb.api.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Table(name="user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity extends BaseEntity implements UserDetails{

    private static final long serialVersionUID = -4988455421375043688L;

	@Column(name="username")
	private String name ;
	
	@Column(name = "password")
	private String password ;
	
	@Column(name = "fullname")
	private String fullname ;
	
	@Column(name = "phone")
	private String phone ;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "status")
	private Integer status ;
	
	public static long getSerialVersionUID() {
        return serialVersionUID;
    }
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
				joinColumns = @JoinColumn(name = "user_id" , nullable = false),
				inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false)) 
	private List<RoleEntity> roles = new ArrayList<>();
	
	
	@ManyToMany(mappedBy = "userEntities" , fetch = FetchType.LAZY)
	private List<BuildingEntity> buildingEntityList = new ArrayList<>();


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
		for(RoleEntity it : getRoles() ) {
			if(it.getCode() != null) {
				authorityList.add(new SimpleGrantedAuthority("ROLE_" + it.getCode()));
			}
		}
		// TODO Auto-generated method stub
		return authorityList;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return name;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.status != 0;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
