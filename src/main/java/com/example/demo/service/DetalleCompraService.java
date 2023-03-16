package com.example.demo.service;

import com.example.demo.model.DetalleCompra;
import java.util.List;
import java.util.Optional;

public interface DetalleCompraService{
    
    public void guardar(DetalleCompra detalleCompra);
    public Optional<DetalleCompra> encontrar(int id);
    public List<DetalleCompra> listar();
    
}
