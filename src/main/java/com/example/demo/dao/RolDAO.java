package com.example.demo.dao;

import com.example.demo.security.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolDAO  extends JpaRepository<Rol, Integer>{}