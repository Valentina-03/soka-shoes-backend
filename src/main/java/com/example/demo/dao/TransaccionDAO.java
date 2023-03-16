package com.example.demo.dao;

import com.example.demo.model.Transaccionp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionDAO extends JpaRepository<Transaccionp, String>{}