package com.javaweb.repository.custom.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public static void join(StringBuilder sql , BuildingSearchBuilder buildingSearchBuilder){
        if (buildingSearchBuilder.getStaffId() != null){
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid  ");
        }
    }
    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder , StringBuilder where){
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if(!fieldName.equals("staffId") && !fieldName.startsWith("area") && !fieldName.equals("typeCode") && !fieldName.startsWith("rentPrice") ) {
                    Object value = item.get(buildingSearchBuilder);
                    if(value != null) {
                        if(item.getType().getName().equals("java.Lang.Long") ) {
                            where.append(" AND b." + fieldName + " = " + value );
                        }
                        else {
                            where.append(" AND b." + fieldName + " like '%" + value + "%'" );
                        }
                    }
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder , StringBuilder where ) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" AND assignmentbuilding.staffid = " + staffId);
        }

        Long rentAreaTo = buildingSearchBuilder.getAreaTo();
        Long rentAreaFrom = buildingSearchBuilder.getAreaFrom();
        if (rentAreaTo != null || rentAreaFrom != null) {
            where.append(" AND EXISTS (SELECT * FROM rentarea r WHERE b.id = r.buildingid");
            if (rentAreaFrom != null) {
                where.append(" AND r.value >= " + rentAreaFrom);
            }
            if (rentAreaTo != null) {
                where.append(" AND r.value <= " + rentAreaTo);
            }
            where.append(" ) ");
        }

        Long rentPriceFrom = buildingSearchBuilder.getRentPriceFrom();
        Long rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceTo != null || rentPriceFrom != null) {
            if (rentPriceFrom != null) {
                where.append(" AND b.rentprice >= " + rentPriceFrom);
            }
            if (rentPriceTo != null) {
                where.append(" AND b.rentprice <= " + rentPriceTo);
            }
        }

        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && typeCode.size() != 0) {
            where.append(" AND (");

            String sql = typeCode.stream().map(it -> " b.type  Like" + "'%" + it + "%'").collect(Collectors.joining(" OR "));
            where.append(sql);
            where.append(" ) ");
        }
    }
    @Override
    public List<BuildingEntity> findJDBCBuilding(BuildingSearchBuilder buildingSearchBuilder) {
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.managername , b.managerphone , b.district , b.numberofbasement , b.street, b.ward, b.floorarea, b.rentprice , b.type FROM building b ");
        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        join(sql, buildingSearchBuilder);
        queryNormal(buildingSearchBuilder, where);
        querySpecial(buildingSearchBuilder, where);
        where.append(" GROUP BY b.id;");
        sql.append(where);
        System.out.println(sql);
        List<BuildingEntity> result = jdbcTemplate.query(sql.toString(),new BeanPropertyRowMapper<>(BuildingEntity.class));
        return result;
    }
}