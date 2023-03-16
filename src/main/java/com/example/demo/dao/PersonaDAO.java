package com.example.demo.dao;

import com.example.demo.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDAO  extends JpaRepository<Persona, Integer>{}