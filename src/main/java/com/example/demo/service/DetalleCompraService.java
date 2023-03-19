package com.example.demo.service;

import com.example.demo.model.Carrito;
import com.example.demo.model.DetalleCompra;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface DetalleCompraService{
    
    public void guardar(DetalleCompra detalleCompra);
    public Optional<DetalleCompra> encontrar(int id);
    public List<DetalleCompra> listar();
	public void eliminar(Integer id);
	public List<DetalleCompra> obtenerPorCarrito(Collection<Carrito> carrito);
    
}
