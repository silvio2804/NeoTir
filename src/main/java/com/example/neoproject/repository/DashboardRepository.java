package com.example.neoproject.repository;

import com.example.neoproject.model.Dashboard;
import com.example.neoproject.model.Postoletto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<Dashboard, Integer> {
    Dashboard findDashboardById(Integer id);
    Dashboard findDashboardByIdpostoletto(Postoletto postoletto);
    Dashboard findByIdpostoletto_Id(Integer id);
    void deleteByIdpostoletto(Postoletto idpostoletto);
}