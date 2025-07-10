package com.javaweb.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.api.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>{
	void deleteByIdIn(List<Long> ids);
}
