package com.example.demo.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.*;

public interface TipoActividadDAO extends JpaRepository<TipoActividad, Integer> {}
