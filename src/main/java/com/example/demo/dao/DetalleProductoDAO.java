package com.example.demo.dao;

import com.example.demo.model.DetalleProducto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleProductoDAO extends JpaRepository<DetalleProducto, Integer> {}