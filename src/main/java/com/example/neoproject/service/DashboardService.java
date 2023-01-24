package com.example.neoproject.service;

import com.example.neoproject.exception.PostolettoNotFoundException;
import com.example.neoproject.map.DashboardMapper;
import com.example.neoproject.map.dtos.dashboard.DashboardGetDto;
import com.example.neoproject.map.dtos.dashboard.DashboardPostDto;
import com.example.neoproject.model.Dashboard;
import com.example.neoproject.model.Postoletto;
import com.example.neoproject.model.Widget;
import com.example.neoproject.repository.DashboardRepository;
import com.example.neoproject.repository.PostolettoRepository;
import com.example.neoproject.repository.SensoreEcgRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private PostolettoRepository postolettoRepository;
    @Autowired
    private SensoreEcgRepository sensoreEcgRepository;
    private DashboardMapper mapper = Mappers.getMapper(DashboardMapper.class);
    private Dashboard dashboard;

    //precondizioni (il posto letto gia esiste)
    //associo i sensori al posto letto e al sensore gli observation (fatto in Sensore service)
    public Dashboard createDashboard(DashboardPostDto dashboardPostDto){
        dashboard = mapper.dashboardPostDtoToDashboard(dashboardPostDto);
        Integer idpostoletto = dashboardPostDto.getIdLetto();
        if(!postolettoRepository.existsById(idpostoletto))
            throw new PostolettoNotFoundException(idpostoletto);

        Postoletto postoletto = postolettoRepository.findPostolettoById(idpostoletto);
        dashboard.setIdpostoletto(postoletto);

        for(Widget w: dashboard.getWidgets())
            w.setIddashboard(dashboard);
        return dashboardRepository.save(dashboard);
    }

    public DashboardGetDto findDashboardByIdPostoletto(Integer idPostoletto){
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        Postoletto postoletto = postolettoRepository.findPostolettoById(idPostoletto);
        return mapper.dashboardToDashboardGetDto(dashboardRepository.findDashboardByIdpostoletto(postoletto));
    }

    public void deleteDashboardByIdPostoletto(Integer idPostoletto){
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        dashboard = dashboardRepository.findByIdpostoletto_Id(idPostoletto);
        Postoletto p = postolettoRepository.findPostolettoById(idPostoletto);
        dashboardRepository.deleteByIdpostoletto(p);
    }

    public void deleteAllDashboards(){
        dashboardRepository.deleteAll();
    }
}
