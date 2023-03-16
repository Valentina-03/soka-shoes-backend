package com.example.demo.dao;

import com.example.demo.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompraDAO extends JpaRepository<Compra, Long> {}