package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface BuildingService{
    ResponseDTO listStaff(Long id);
    List<BuildingSearchResponse> listBuildings(Map<String,Object> params, List<String> typeCode);
    void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
    void deleteBuilding(Long id);
    BuildingDTO editBuilding(Long id);
    void addOrUpdateBuilding(BuildingDTO buildingDTO);
    List<BuildingSearchResponse> findAllBuildings();
}
