package com.example.demo.service;

import com.example.demo.model.DetalleProducto;
import java.util.List;
import java.util.Optional;

public interface DetalleProductoService{
    
    public void guardar(DetalleProducto detalleProducto);
    public Optional<DetalleProducto> encontrar(int id);
    public List<DetalleProducto> listar();
    
}
