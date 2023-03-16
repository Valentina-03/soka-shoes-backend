package com.example.demo.dao;

import com.example.demo.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaDAO extends JpaRepository<Marca, Integer>{}