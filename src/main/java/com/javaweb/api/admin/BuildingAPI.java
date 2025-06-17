package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;

import com.javaweb.model.response.ResponseDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController(value = "buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {

    @Autowired
    @Lazy
    private BuildingService buildingService;
    @Autowired
    private BuildingRepository buildingRepository;


    @PostMapping
    public BuildingDTO addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        return buildingDTO;
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable List<Long> ids){
        System.out.println("ok");
    }

    @GetMapping("/staffs/{id}")
    public ResponseDTO listStaffs(@PathVariable Long id){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO = buildingService.listStaff(id);
        return responseDTO;
    }

    @PostMapping("/assignment")
    public void assignment(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        buildingService.assignmentBuilding(assignmentBuildingDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBuilding(@PathVariable Long id){
        buildingService.deleteBuilding(id);
    }

    @PostMapping(value = "/addOrUpdate")
    public void addOrUpdate(@RequestBody BuildingDTO buildingDTO){
        buildingService.addOrUpdateBuilding(buildingDTO);
    }
}
