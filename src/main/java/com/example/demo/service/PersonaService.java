package com.example.demo.service;

import com.example.demo.model.Persona;
import java.util.List;
import java.util.Optional;

public interface PersonaService {
        
    public void guardar(Persona persona);
    public void eliminar(int id);
    public Optional<Persona> encontrar(int id);
    public List<Persona> listar();
}
