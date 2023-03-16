package com.example.demo.dao;

import com.example.demo.model.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraDAO extends JpaRepository<DetalleCompra, Integer> {}