package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingResponseConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    @Lazy
    private BuildingRepository buildingRepository;
    @Autowired
    @Lazy
    private UserRepository userRepository;
    @Autowired
    @Lazy
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;
    @Autowired
    private BuildingResponseConverter buildingResponseConverter;
    @Autowired
    private BuildingRepositoryCustom buildingRepositoryCustom;
    @Autowired
    private RentAreaRepository rentAreaRepository;



    @Override
    public ResponseDTO listStaff(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        List<UserEntity>  allUser = userRepository.findByStatusAndRoles_Code(1 , "STAFF");
        List<UserEntity> userEntities = buildingEntity.getUserEntities();
        List<StaffResponseDTO> staffResponseDTOs = new ArrayList<>();
        ResponseDTO responseDTO = new ResponseDTO();
        for (UserEntity it : allUser) {
            StaffResponseDTO staff = new StaffResponseDTO();
            staff.setFullName(it.getFullName());
            staff.setStaffId(it.getId());
            if(userEntities.contains(it)){
                staff.setChecked("Checked");
            }
            else{
                staff.setChecked("");
            }

            staffResponseDTOs.add(staff);
        }
        responseDTO.setData(staffResponseDTOs);
        responseDTO.setMessage("success");
        return responseDTO;
    }

    @Override
    public List<BuildingSearchResponse> listBuildings(Map<String, Object> params, List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params,typeCode);
        List<BuildingEntity> buildingEntities = buildingRepositoryCustom.findJDBCBuilding(buildingSearchBuilder);
        System.out.print(buildingEntities.size());
        List<BuildingSearchResponse> result = new ArrayList<>();
        for(BuildingEntity it : buildingEntities){
            BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
            buildingSearchResponse = buildingResponseConverter.toBuildingSearchRequest(it);
            result.add(buildingSearchResponse);
        }
        System.out.print(result.size());
        return result;
    }

    @Override
    public void assignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        List<UserEntity> staffs = userRepository.findByIdIn(assignmentBuildingDTO.getStaffs());
        buildingEntity.setUserEntities(staffs);
        buildingRepository.save(buildingEntity);
    }

    @Transactional
    @Override
    public void deleteBuilding(Long id) {
        buildingRepository.deleteById(id);
    }

    @Override
    public BuildingDTO editBuilding(Long id) {
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        List<RentAreaEntity> rentAreas = buildingEntity.getRentAreaEntities();
        List<String>  listRentArea= new ArrayList<>();
        for(RentAreaEntity it : rentAreas){
            listRentArea.add(it.getValue());
        }
        String stringRentArea = String.join("," , listRentArea);
        List<String> typeCode = Arrays.asList(buildingEntity.getTypeCode().split(","));
        BuildingDTO buildingDTO = buildingResponseConverter.toBuildingDTO(buildingEntity);
        buildingDTO.setTypeCode(typeCode);
        buildingDTO.setRentArea(stringRentArea);
        return buildingDTO;
    }

    @Transactional
    @Override
    public void addOrUpdateBuilding(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = new BuildingEntity();
        buildingEntity = buildingResponseConverter.toEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public List<BuildingSearchResponse> findAllBuildings() {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll();
        List<BuildingSearchResponse> result = new ArrayList<>();
        for(BuildingEntity it : buildingEntities){
            BuildingSearchResponse buildingSearchResponse = new BuildingSearchResponse();
            buildingSearchResponse = buildingResponseConverter.toBuildingSearchRequest(it);
            result.add(buildingSearchResponse);
        }
        return result;
    }


}
