package com.example.demo.service;

import com.example.demo.model.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionService {
        
    public void guardar(Direccion direccion);
    public void eliminar(int id);
    public Optional<Direccion> encontrar(int id);
    public List<Direccion> listar();
}
