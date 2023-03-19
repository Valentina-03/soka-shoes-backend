package com.example.demo.dao;

import com.example.demo.model.DetalleProducto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DetalleProductoDAO extends JpaRepository<DetalleProducto, Integer> 
{
	@Query(value = "SELECT ALL id_detalle FROM detalle_producto WHERE producto = :producto AND color = :color", nativeQuery = true)
    List<Integer> getProductsByProdCol(@Param("producto") Integer p, @Param("color") String c);
	
	@Query(value = "SELECT ALL id_detalle FROM detalle_producto WHERE producto = :producto AND talla = :talla", nativeQuery = true)
    List<Integer> getProductsByProdTal(@Param("producto") Integer p, @Param("talla") Integer t);
	
	@Query(value = "SELECT ALL id_detalle FROM detalle_producto WHERE color = :color AND talla = :talla", nativeQuery = true)
    List<Integer> getProductsByColTal(@Param("color") String c, @Param("talla") Integer t);
	
	@Query(value = "SELECT id_detalle FROM detalle_producto WHERE producto = :producto AND color = :color AND talla = :talla LIMIT 1", nativeQuery = true)
    Integer getProductsByAll(@Param("producto") Integer p, @Param("color") String c, @Param("talla") Integer t);
}