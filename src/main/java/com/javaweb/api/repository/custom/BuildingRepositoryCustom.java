package com.javaweb.api.repository.custom;

import java.util.List;

import com.javaweb.api.builder.BuildingSearchBuilder;
import com.javaweb.api.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder);
}
