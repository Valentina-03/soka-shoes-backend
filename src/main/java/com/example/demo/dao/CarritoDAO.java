package com.example.demo.dao;

import com.example.demo.model.Carrito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarritoDAO extends JpaRepository<Carrito, Long>{}