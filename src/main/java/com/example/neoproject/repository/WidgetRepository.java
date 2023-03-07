package com.example.neoproject.repository;

import com.example.neoproject.model.Widget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WidgetRepository extends JpaRepository<Widget, Integer> {
}