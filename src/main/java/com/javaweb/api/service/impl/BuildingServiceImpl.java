package com.javaweb.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaweb.api.builder.BuildingSearchBuilder;
import com.javaweb.api.converter.BuildingResponseConverter;
import com.javaweb.api.converter.BuildingSearchBuilderConverter;
import com.javaweb.api.entity.BuildingEntity;
import com.javaweb.api.entity.UserEntity;
import com.javaweb.api.model.dto.AssignmentBuildingDTO;
import com.javaweb.api.model.dto.BuildingDTO;
import com.javaweb.api.model.response.BuildingSearchResponse;
import com.javaweb.api.model.response.StaffResponseDTO;
import com.javaweb.api.repository.BuildingRepository;
import com.javaweb.api.repository.UserRepository;
import com.javaweb.api.repository.custom.BuildingRepositoryCustom;
import com.javaweb.api.service.BuildingService;


@Service
public class BuildingServiceImpl implements BuildingService{

	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private BuildingResponseConverter buildingResponseConverter;
	@Autowired
	private BuildingRepositoryCustom buildingRepositoryCustom ;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	@Override
	public void deleteBuilding(List<Long> id) {
		buildingRepository.deleteByIdIn(id);
	}

	@Override
	public List<BuildingSearchResponse> searchBuilding(Map<String,Object> params , List<String> typeCode) {
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> all = buildingRepositoryCustom.searchBuilding(buildingSearchBuilder);
		List<BuildingSearchResponse> result = new ArrayList<>();
		for(BuildingEntity it : all) {
			BuildingSearchResponse building = buildingResponseConverter.toBuildingResponse(it);
			result.add(building);
		}
		return result;
	}

    @Transactional
	@Override
	public void addOrUpdate(BuildingDTO buildingDTO) {
		// TODO Auto-generated method stub
		BuildingEntity buildingEntity = buildingResponseConverter.toBuildingEntity(buildingDTO);
		buildingRepository.save(buildingEntity);
	}
    
    @Transactional
	@Override
	public List<StaffResponseDTO> listStaff(Long id) {
		// TODO Auto-generated method stub
		BuildingEntity buildingEntity = buildingRepository.findById(id).get();
		List<UserEntity> listStaff = userRepository.findByStatusAndRoles_Code(1,"STAFF");
		List<UserEntity> userBuilding = buildingEntity.getUserEntities();
		List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>();
		for(UserEntity it : listStaff) {
			StaffResponseDTO staff = new StaffResponseDTO();
			staff.setFullName(it.getFullname());
			staff.setStaffId(it.getId());
			if(userBuilding.contains(it)) {
				staff.setChecked("Checked");
			}
			else {
				staff.setChecked("");
			}
			staffResponseDTOs.add(staff);
		}
		return staffResponseDTOs;
	}

    @Transactional
	@Override
	public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
		// TODO Auto-generated method stub
		BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
		List<UserEntity> userEntity = userRepository.findByIdIn(assignmentBuildingDTO.getStaffIds());
		buildingEntity.setUserEntities(userEntity);
		buildingRepository.save(buildingEntity);
	}
}
