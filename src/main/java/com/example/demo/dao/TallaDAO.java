package com.example.demo.dao;

import com.example.demo.model.Talla;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TallaDAO extends JpaRepository<Talla, Integer>{
	@Query(value = "SELECT DISTINCT producto FROM detalle_producto WHERE talla = :talla", nativeQuery = true)
    List<Integer> getProductsByTalla(@Param("talla") Integer id);
}