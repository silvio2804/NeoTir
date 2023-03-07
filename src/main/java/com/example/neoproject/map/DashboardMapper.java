package com.example.neoproject.map;

import com.example.neoproject.map.dtos.dashboard.DashboardGetDto;
import com.example.neoproject.map.dtos.dashboard.DashboardPostDto;
import com.example.neoproject.map.dtos.postoletto.PostoLettoGetDto;
import com.example.neoproject.map.dtos.postoletto.PostolettoDto;
import com.example.neoproject.map.dtos.widget.WidgetDto;
import com.example.neoproject.map.dtos.widget.WidgetPostDto;
import com.example.neoproject.model.Dashboard;
import com.example.neoproject.model.Postoletto;
import com.example.neoproject.model.Widget;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface DashboardMapper {
    Dashboard dashboardPostDtoToDashboard(DashboardPostDto dashboardPostDto);
    DashboardGetDto dashboardToDashboardGetDto(Dashboard dashboard);

    //PostoLettoGetDto postoLettoToPostoLettoGetDto(Postoletto postoletto);
    Postoletto postolettoDtoToPostoletto(PostolettoDto postolettoDto);
    PostolettoDto postolettoToPostolettoDto(Postoletto postoletto);

    List <DashboardGetDto> dashboardsToDashboardsGetDto(List<Dashboard> dashboardList);

    Widget widgetPostDtoToWidget(WidgetPostDto widgetPostDto);
    Widget widgetDtoToWidget(WidgetDto widgetDto);
    WidgetDto widgetToWidgetDto (Widget widget);
}
