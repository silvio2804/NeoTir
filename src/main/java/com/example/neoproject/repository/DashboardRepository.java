package com.example.neoproject.repository;

import com.example.neoproject.model.Dashboard;
import com.example.neoproject.model.Postoletto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    Dashboard findDashboardById(Integer id);
    Dashboard findDashboardByIdpostoletto(Postoletto postoletto);

    List<Dashboard> findByIdpostoletto_Id(Integer id);


    void deleteByIdpostoletto(Postoletto idpostoletto);

}