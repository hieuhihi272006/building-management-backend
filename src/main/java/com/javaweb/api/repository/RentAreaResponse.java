package com.javaweb.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.api.entity.RentEntity;

public interface RentAreaResponse extends JpaRepository<RentEntity, Long>{
	List<RentEntity> findByBuildingId(Long id);
	void deleteByBuildingId(Long id);
}
