package com.example.demo.dao;

import com.example.demo.model.Direccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DireccionDAO extends JpaRepository<Direccion, Integer>{}