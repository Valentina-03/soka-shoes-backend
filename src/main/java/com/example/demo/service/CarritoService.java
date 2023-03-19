package com.example.demo.service;

import com.example.demo.model.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoService {
        
    public void guardar(Carrito carrito);
    public void eliminar(Long id);
    public Optional<Carrito> encontrar(Long id);
    public List<Carrito> listar();
}
