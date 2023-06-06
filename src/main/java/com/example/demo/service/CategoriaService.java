package com.example.demo.service;

import com.example.demo.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
        
    public void guardar(Categoria categoria);
    public void eliminar(int id);
    public Optional<Categoria> encontrar(int id);
    public List<Categoria> listar();
    public Object getImg(Integer id);
}
