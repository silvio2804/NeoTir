package com.example.neoproject.service;

import com.example.neoproject.exception.DashboardException;
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
import com.example.neoproject.repository.WidgetRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;
    @Autowired
    private PostolettoRepository postolettoRepository;

    private DashboardMapper mapper = Mappers.getMapper(DashboardMapper.class);
    @Autowired
    private WidgetRepository widgetRepository;

    //precondizioni (il posto letto gia esiste)
    //associo i sensori al posto letto e al sensore gli observation (fatto in Sensore service)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Dashboard createDashboard(DashboardPostDto dashboardPostDto){

        Integer idpostoletto = dashboardPostDto.getIdLetto();
        if(!postolettoRepository.existsById(idpostoletto))
            throw new PostolettoNotFoundException(idpostoletto);

        Dashboard dashboard = mapper.dashboardPostDtoToDashboard(dashboardPostDto);

        Postoletto postoletto = postolettoRepository.findPostolettoById(idpostoletto);
        dashboard.setIdpostoletto(postoletto);

        List <Widget> widgets = dashboardPostDto.getWidgets().stream().map(mapper::widgetPostDtoToWidget).collect(Collectors.toList());
        dashboard.setWidgets(widgets);

        for(Widget w: widgets)
            w.setIddashboard(dashboard);
        dashboard.setWidgets(widgets);

        dashboardRepository.save(dashboard);
        return dashboard;
    }

    public List<DashboardGetDto> findDashboardByIdPostoletto(Integer idPostoletto){
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        List<Dashboard> d = dashboardRepository.findByIdpostoletto_Id(idPostoletto);
        return mapper.dashboardsToDashboardsGetDto(dashboardRepository.findByIdpostoletto_Id(idPostoletto));
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public Dashboard updateDashboard(DashboardPostDto dashboardPostDto, Integer idDashboard) {
        if (!dashboardRepository.existsById(idDashboard))
            throw new DashboardException(idDashboard);

        Dashboard dashboardDb = dashboardRepository.findDashboardById(idDashboard);
        dashboardDb.getWidgets().clear();

        List<Widget> widgetsFromDto = dashboardPostDto.getWidgets().stream().map(mapper::widgetPostDtoToWidget).collect(Collectors.toList());

        for (Widget widget: widgetsFromDto)
            widget.setIddashboard(dashboardDb);

        dashboardDb.getWidgets().addAll(widgetsFromDto);
        return dashboardRepository.save(dashboardDb);
    }

    @Transactional
    public void deleteDashboardByIdPostoletto(Integer idPostoletto){
        if(!postolettoRepository.existsById(idPostoletto))
            throw new PostolettoNotFoundException(idPostoletto);
        Postoletto p = postolettoRepository.findPostolettoById(idPostoletto);
        dashboardRepository.deleteByIdpostoletto(p);
    }
    @Transactional
    public void deleteAllDashboards(){
        dashboardRepository.deleteAll();
    }
}
