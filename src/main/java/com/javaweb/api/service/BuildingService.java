package com.javaweb.api.service;

import java.util.List;
import java.util.Map;

import com.javaweb.api.model.dto.AssignmentBuildingDTO;
import com.javaweb.api.model.dto.BuildingDTO;
import com.javaweb.api.model.response.BuildingSearchResponse;
import com.javaweb.api.model.response.StaffResponseDTO;

public interface BuildingService {
	void deleteBuilding(List<Long> id);
	List<BuildingSearchResponse> searchBuilding(Map<String,Object> params, List<String> typeCode);
	void addOrUpdate(BuildingDTO buildingDTO);
	List<StaffResponseDTO> listStaff(Long id);
	void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);

}
