package com.javaweb.api.converter;

	import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.api.entity.BuildingEntity;
import com.javaweb.api.entity.RentEntity;
import com.javaweb.api.model.dto.BuildingDTO;
import com.javaweb.api.model.response.BuildingSearchResponse;
import com.javaweb.api.repository.RentAreaResponse;

@Component
public class BuildingResponseConverter {

	@Autowired
	private ModelMapper modelMapper ;
	@Autowired
	private RentAreaResponse rentAreaResponse ;

	public BuildingSearchResponse toBuildingResponse(BuildingEntity it) {
		// TODO Auto-generated method stub
		BuildingSearchResponse building = modelMapper.map(it, BuildingSearchResponse.class);
		building.setAddress(it.getStreet() + "," + it.getWard() + "," + it.getDistrict());
		List<RentEntity> rentEntities = rentAreaResponse.findByBuildingId(it.getId());
		String rentResult = rentEntities.stream().map(item -> item.getValue().toString()).collect(Collectors.joining(","));
		building.setRentArea(rentResult);
		return building;
	}
	
	public BuildingEntity toBuildingEntity(BuildingDTO buildingDTO){
		BuildingEntity buildingEntity = modelMapper.map(buildingDTO , BuildingEntity.class);
		if(buildingDTO.getId() != null) {
			rentAreaResponse.deleteByBuildingId(buildingDTO.getId());
		}
		String typeCode = String.join(",",buildingDTO.getTypeCode());
		buildingEntity.setTypeCode(typeCode);
		List<RentEntity> rentEntities = new ArrayList<>();
		List<String> listRent = Arrays.asList(buildingDTO.getRentArea().split(","));
		for(String it : listRent) {
			RentEntity rentEntity = new RentEntity();
			rentEntity.setValue(it);
			rentEntity.setBuilding(buildingEntity);
			rentEntities.add(rentEntity);
		}
		buildingEntity.setRentEntities(rentEntities);
		return buildingEntity;
	}
	
	
}
