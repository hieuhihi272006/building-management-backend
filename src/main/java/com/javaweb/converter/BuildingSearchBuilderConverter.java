
package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.utils.MapUtils;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params , List<String> typeCode) {
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
                .setName(MapUtils.getObject(params, "name", String.class))
                .setFloorArea(MapUtils.getObject(params,"floorArea", Long.class))
                .setWard(MapUtils.getObject(params, "ward", String. class))
                .setStreet(MapUtils.getObject(params, "street", String. class))
                .setDistrict(MapUtils.getObject(params,"district", String. class))
                .setNumberOfBasement(MapUtils.getObject(params, "numberOfBasement", Long.class))
                .setTypeCode(typeCode)
                .setManagerName(MapUtils.getObject(params, "managerName", String. class))
                .setManagerPhoneNumber(MapUtils.getObject(params, "managerPhoneNumber", String.class))
                .setRentPriceTo(MapUtils.getObject(params, "rentPriceTo", Long.class))
                .setRentPriceFrom(MapUtils.getObject(params, "rentPriceFrom", Long.class))
                .setAreaFrom(MapUtils.getObject(params, "areaFrom", Long.class))
                .setAreaTo(MapUtils.getObject(params, "areaTo", Long.class))
                .setStaffId(MapUtils.getObject(params, "staffId", Long.class))
                .setLevel(MapUtils.getObject(params, "level", String.class))
                .setDirection(MapUtils.getObject(params, "direction", String.class))
                .build();
        return buildingSearchBuilder;
    }
}
