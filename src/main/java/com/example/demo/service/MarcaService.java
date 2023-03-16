package com.example.demo.service;

import com.example.demo.model.Marca;
import java.util.List;
import java.util.Optional;

public interface MarcaService {
    public void guardar(Marca marca);
    public Optional<Marca> encontrar(int id);
    public void eliminar(int id);
    public List<Marca> listar();
}
