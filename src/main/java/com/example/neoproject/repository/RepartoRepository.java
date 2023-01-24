package com.example.neoproject.repository;

import com.example.neoproject.model.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepartoRepository extends JpaRepository<Reparto, String> {

    Reparto findRepartoById(String nomeReparto);
}