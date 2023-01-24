package com.example.neoproject.controller;

import com.example.neoproject.map.dtos.dashboard.DashboardGetDto;
import com.example.neoproject.map.dtos.dashboard.DashboardPostDto;
import com.example.neoproject.model.Dashboard;
import com.example.neoproject.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RequestMapping("/api")
@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    //ok
    @GetMapping("/dashboard/{idPostoletto}")
    public ResponseEntity<DashboardGetDto> getDashboard(@PathVariable Integer idPostoletto){
        return new ResponseEntity<>(dashboardService.findDashboardByIdPostoletto(idPostoletto), HttpStatus.OK);
    }

    //ok
    @PostMapping("/dashboard")
    public ResponseEntity<Integer> createDashboard(@RequestBody DashboardPostDto dashboardPostDto){
        return new ResponseEntity(dashboardService.createDashboard(dashboardPostDto),HttpStatus.CREATED);
    }
    @DeleteMapping("/dashboard/delete/{idPostoletto}")
    @Transactional
    public ResponseEntity<HttpStatus> deleteDashboard(@PathVariable Integer idPostoletto){
       dashboardService.deleteDashboardByIdPostoletto(idPostoletto);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/dashboard/deleteAll")
    @Transactional
    public ResponseEntity<HttpStatus> deleteAllDashboards(){
        dashboardService.deleteAllDashboards();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
