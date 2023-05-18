package com.example.demo.service;

import com.example.demo.model.DetalleProducto;
import java.util.List;
import java.util.Optional;

public interface DetalleProductoService
{    
    public void guardar(DetalleProducto detalleProducto);
    public Optional<DetalleProducto> encontrar(Integer id);
    public List<DetalleProducto> listar();
	public void eliminar(Integer id);
	public List<DetalleProducto> listarProductosPorProdCol(Integer p, String c);
	public List<DetalleProducto> listarProductosPorProdTal(Integer p, Integer t);
	public List<DetalleProducto> listarProductosPorColTal(String c, Integer t);
	public DetalleProducto listarProductosPorTodo(Integer p, String c, Integer t);    
}