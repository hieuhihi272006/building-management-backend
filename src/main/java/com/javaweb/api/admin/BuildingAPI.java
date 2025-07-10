package com.javaweb.api.admin;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaweb.api.model.dto.AssignmentBuildingDTO;
import com.javaweb.api.model.dto.BuildingDTO;
import com.javaweb.api.model.response.BuildingSearchResponse;
import com.javaweb.api.model.response.StaffResponseDTO;
import com.javaweb.api.service.BuildingService;



@RestController
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private BuildingService buildingService;
	
	
    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteBuilding(@RequestParam(name = "ids" , required = false) List<Long> ids) {
    	buildingService.deleteBuilding(ids);
    	return ResponseEntity.ok("");
    }
    
    @GetMapping(value = "/search")
    public ResponseEntity<List<BuildingSearchResponse>> searchBuilding(@RequestParam Map<String,Object> params,
    																	@RequestParam(name = "typeCode" , required = false) List<String> typeCode){
    		List<BuildingSearchResponse> result = buildingService.searchBuilding(params,typeCode);
    		return ResponseEntity.ok(result);
    }
    
    @PostMapping(value = "/add_update")
    public ResponseEntity<?> addOrUpdate(@RequestBody BuildingDTO buildingDTO) {
    	buildingService.addOrUpdate(buildingDTO);
    	return ResponseEntity.ok("");
    }
    
    @GetMapping(value = "/staffs/{id}")
    public ResponseEntity<List<StaffResponseDTO>> listStaff(@PathVariable Long id){
    	List<StaffResponseDTO> listStaff = buildingService.listStaff(id);
    	return ResponseEntity.ok(listStaff);
    }
    
    @PutMapping(value = "/assignmentbuilding")
    public ResponseEntity<?> assignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO) {
    	buildingService.assignmentBuilding(assignmentBuildingDTO);
    	return ResponseEntity.ok("");
    }
    
    @GetMapping("/test")
    public ResponseEntity<?> testToken() {
        return ResponseEntity.ok("Token OK");
    }
}
