package com.example.demo.service;

import com.example.demo.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
        
    public void guardar(Producto persona);
    public void eliminar(int id);
    public Optional<Producto> encontrar(int id);
    public List<Producto> listar();
}
