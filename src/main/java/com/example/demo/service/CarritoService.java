package com.example.demo.service;

import com.example.demo.model.Carrito;

import java.util.List;
import java.util.Optional;

public interface CarritoService {
        
    public void guardar(Carrito carrito);
    public void eliminar(int id);
    public Optional<Carrito> encontrar(int id);
    public List<Carrito> listar();
}
