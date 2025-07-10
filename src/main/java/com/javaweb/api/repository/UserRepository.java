package com.javaweb.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.api.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	List<UserEntity> findByStatusAndRoles_Code(Integer st , String check);
	List<UserEntity> findByIdIn(List<Long> id);
	Optional<UserEntity> findByName(String name);
	boolean existsByName(String name);

}
