package com.example.neoproject.repository;

import com.example.neoproject.model.Neonato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NeonatoRepository extends JpaRepository<Neonato, Integer> {
    Neonato findNeonatoById(Integer id);
}