package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component

public class BuildingResponseConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    public BuildingSearchResponse toBuildingSearchRequest(BuildingEntity item){
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        building.setAddress(item.getStreet()+"," + item.getWard()+"," +item.getDistrict());
        List<RentAreaEntity> areaEntitys =  rentAreaRepository.findByBuildingId(item.getId());
        String areaResult =  areaEntitys.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(areaResult);
        return building;
    }

    public BuildingDTO toBuildingDTO(BuildingEntity item){
        BuildingDTO buildingDTO = modelMapper.map(item , BuildingDTO.class);
        return buildingDTO;
    }

    public BuildingEntity toEntity(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        if(buildingDTO.getId() != null){
            rentAreaRepository.deleteByBuildingId(buildingDTO.getId());
        }
        String typeCode = String.join(",",buildingDTO.getTypeCode());
        buildingEntity.setTypeCode(typeCode);
        List<RentAreaEntity> rentAreas = new ArrayList<>();
        List<String> listArea = Arrays.asList(buildingDTO.getRentArea().split(","));
        for(String it: listArea){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setValue(it);
            rentAreaEntity.setBuildingEntity(buildingEntity);
            rentAreas.add(rentAreaEntity);
        }
        buildingEntity.setRentAreaEntities(rentAreas);
        return buildingEntity;
    }
}
