package com.example.demo.dao;

import com.example.demo.model.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorDAO extends JpaRepository<Color, String>{}
