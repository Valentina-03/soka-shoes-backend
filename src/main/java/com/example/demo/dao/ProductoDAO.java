package com.example.demo.dao;

import com.example.demo.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDAO extends JpaRepository<Producto, Integer>
{
	@Query(value = "SELECT id_producto FROM producto WHERE estado = 1", nativeQuery = true)
	List<Integer> getDisponibles();
}