package com.example.demo.dao;

import com.example.demo.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartamentoDAO extends JpaRepository<Departamento, Integer>{}