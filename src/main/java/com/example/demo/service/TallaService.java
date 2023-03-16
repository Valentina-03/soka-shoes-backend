package com.example.demo.service;

import com.example.demo.model.Talla;

import java.util.List;
import java.util.Optional;

public interface TallaService {
        
    public void guardar(Talla talla);
    public void eliminar(int id);
    public Optional<Talla> encontrar(int id);
    public List<Talla> listar();
}
