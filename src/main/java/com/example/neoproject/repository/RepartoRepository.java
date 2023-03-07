package com.example.neoproject.repository;

import com.example.neoproject.model.Reparto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepartoRepository extends JpaRepository<Reparto, String> {

    Reparto findRepartoById(String nomeReparto);

    @Override
    Optional<Reparto> findById(String s);
}