package com.javaweb.api.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.api.builder.BuildingSearchBuilder;
import com.javaweb.api.utils.MapUtil;

@Component
public class BuildingSearchBuilderConverter {

	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String , Object> params , List<String> typeCode) {
		BuildingSearchBuilder result = new BuildingSearchBuilder.Builder()
										.setName(MapUtil.getObject(params, "name", String.class))
						                .setFloorArea(MapUtil.getObject(params,"floorArea", Long.class))
						                .setWard(MapUtil.getObject(params, "ward", String. class))
						                .setStreet(MapUtil.getObject(params, "street", String. class))
						                .setDistrict(MapUtil.getObject(params,"district", String. class))
						                .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Long.class))
						                .setTypeCode(typeCode)
						                .setManagerName(MapUtil.getObject(params, "managerName", String. class))
						                .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", String.class))
						                .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Long.class))
						                .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Long.class))
						                .setAreaFrom(MapUtil.getObject(params, "areaFrom", Long.class))
						                .setAreaTo(MapUtil.getObject(params, "areaTo", Long.class))
						                .setStaffId(MapUtil.getObject(params, "staffId", Long.class))
						                .setLevel(MapUtil.getObject(params, "level", String.class))
						                .setDirection(MapUtil.getObject(params, "direction", String.class))
										.build();
		return result;
	}
}
