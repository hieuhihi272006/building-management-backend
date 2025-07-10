package com.javaweb.api.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.javaweb.api.builder.BuildingSearchBuilder;
import com.javaweb.api.entity.BuildingEntity;
import com.javaweb.api.repository.custom.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom{

	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	  
	public static void jointable(StringBuilder sql , BuildingSearchBuilder buildingSearchBuilder) {
		if(buildingSearchBuilder.getStaffId() != null) {
			sql.append(" INNER JOIN assignmentbuilding ON assignmentbuilding.buildingid = b.id");
		}
	}
	
	public static void queryNormal(StringBuilder where , BuildingSearchBuilder buildingSearchBuilder) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for(Field it : fields) {
				it.setAccessible(true);
				String fieldName = it.getName();
				if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentPrice") &&!fieldName.startsWith("area")) {
					Object value = it.get(buildingSearchBuilder);
					if(value != null) {
						if(it.getType().getName().equals("java.lang.String")) {
							where.append(" AND b." + fieldName + " LIKE '%" + value + "%'");
						}
						else {
							where.append(" AND b." + fieldName + " = " + value);
						}
					}
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public static void querySpecial(StringBuilder where , BuildingSearchBuilder buildingSearchBuilder) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if(staffId != null) {
			where.append(" AND assignmentbuilding.staffid = " + staffId);
		}
		
		Long areaFrom = buildingSearchBuilder.getAreaFrom();
		Long areaTo = buildingSearchBuilder.getAreaTo();
		if(areaFrom != null || areaTo != null) {
			where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid");
			if(areaFrom != null) {
				where.append(" AND r.value >= " + areaFrom);
			}
			if(areaTo != null) {
				where.append(" AND r.value <= " + areaTo);
			}
			where.append(" )");
		}
		
		Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
		Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
		if(rentPriceFrom != null || rentPriceTo != null) {
			if(rentPriceFrom != null) {
				where.append(" AND b.rentprice >= " + rentPriceFrom );
			}
			if(rentPriceTo != null) {
				where.append(" AND b.rentprice <= " + rentPriceTo );
			}
		}
		
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode != null && typeCode.size() != 0) {
			where.append(" AND ( ");
			String cnt = typeCode.stream().map(it -> " b.type LIKE '%" + it + "%'").collect(Collectors.joining("OR"));
			where.append(cnt);
			where.append(" )");
		}
	}
	
	@Override
	public List<BuildingEntity> searchBuilding(BuildingSearchBuilder buildingSearchBuilder) {
		// TODO Auto-generated method stub
		StringBuilder sql = new StringBuilder("SELECT b.id , b.name , b.street , b.ward , b.district , b.numberofbasement , b.floorarea , b.rentprice , b.rentpricedescription , b.type , b.managername , b.managerphone FROM building b");
		StringBuilder where = new StringBuilder(" WHERE 1=1 ");
		jointable(sql,buildingSearchBuilder);
		queryNormal(where, buildingSearchBuilder);
		querySpecial(where,buildingSearchBuilder);
		where.append(" GROUP BY b.id");
		sql.append(where);
		System.out.println(sql);
		List<BuildingEntity> result = jdbcTemplate.query(sql.toString() , new BeanPropertyRowMapper<>(BuildingEntity.class));	
		return result;
	}

	

}
