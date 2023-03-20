package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.model.ProductoDeatllesDto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
        
    public void guardar(Producto persona);
    public void eliminar(int id);
    public Optional<Producto> encontrar(int id);
    public List<Producto> listar();
	public List<Producto> listarDisponibles();
	public List<ProductoDeatllesDto> obtenerDetalles(Integer id);
	public List<Producto> filtrar(Integer marca, Integer categoria, String color, Integer talla, Double precio_min, Double precio_max);
	public int obtenerDetalle(Integer id, String color, Integer talla, Integer cantidad);
}
