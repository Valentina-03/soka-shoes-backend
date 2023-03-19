package com.example.demo.dao;

import com.example.demo.model.Color;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ColorDAO extends JpaRepository<Color, String>
{
	@Query(value = "SELECT DISTINCT producto FROM detalle_producto WHERE color = :color", nativeQuery = true)
    List<Integer> getProductsByColor(@Param("color") String id);
}