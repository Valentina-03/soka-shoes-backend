package com.example.demo.dao;

import com.example.demo.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDAO extends JpaRepository<Producto, Integer>{}