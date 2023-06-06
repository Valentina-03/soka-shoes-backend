package com.example.demo.dao;

import com.example.demo.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoriaDAO extends JpaRepository<Categoria, Integer>{
	@Query(value = "SELECT dp.url_img, dp.id_detalle FROM detalle_producto dp JOIN producto p ON p.id_producto = dp.producto " +
					"JOIN categoria c ON c.id_categoria = p.categoria WHERE c.id_categoria = :id LIMIT 1", nativeQuery = true)
	public Object getImg(@Param("id") Integer id);
	
}
