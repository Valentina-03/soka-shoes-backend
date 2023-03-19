package com.example.demo.dao;

import com.example.demo.model.Compra;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CompraDAO extends JpaRepository<Compra, Long> 
{
	@Query(value = "SELECT DISTINCT id_compra FROM compra WHERE estado = :estado", nativeQuery = true)
    List<Long> getProductsByColor(@Param("estado") String estado);
}