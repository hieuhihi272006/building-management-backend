package com.javaweb.controller.admin;



import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.enums.District;
import com.javaweb.enums.TypeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping(value="/admin/building-list")
    public ModelAndView buildinglist(@ModelAttribute BuildingSearchRequest buildingSearchRequest, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("modelSearch" , buildingSearchRequest);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        List<UserEntity> listUser = userRepository.findAll();
        mav.addObject("listStaffs" , listUser);
        List<BuildingSearchResponse> listBuildings = buildingService.findAllBuildings();
        mav.addObject("buildingList" , listBuildings);
        return mav;
    }

    @GetMapping(value="/api/building-search")
    public ModelAndView searchBuilding(@RequestParam Map<String,Object> params ,
                                       @RequestParam(name = "typeCode" , required =false)  List<String> typeCode,
                                       HttpServletRequest request,
                                       BuildingSearchRequest buildingSearchRequest){
        ModelAndView mav = new ModelAndView("admin/building/list");
        if(params.isEmpty()){
            return new ModelAndView("admin/building/list");
        }
        List<BuildingSearchResponse> listBuildings = buildingService.listBuildings(params,typeCode);
        mav.addObject("modelSearch" , buildingSearchRequest);
        mav.addObject("districts", District.type());
        mav.addObject("typeCodes", TypeCode.type());
        mav.addObject("buildingList" , listBuildings);
        return mav ;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView editBuilding(@PathVariable Long id , BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        buildingDTO = buildingService.editBuilding(id);
        mav.addObject("districts",District.type());
        mav.addObject("typeCodes",TypeCode.type());
        mav.addObject("buildingEdit" , buildingDTO);
        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView addBuilding(BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districts",District.type());
        mav.addObject("typeCodes",TypeCode.type());
        mav.addObject("buildingEdit" , buildingDTO);
        return mav;
    }



}
