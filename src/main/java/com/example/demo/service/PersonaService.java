package com.example.demo.service;

import com.example.demo.model.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaService {
        
    public void guardar(Persona persona);
    public void eliminar(String id);
    public Optional<Persona> encontrar(String id);
    public List<Persona> listar();
}
