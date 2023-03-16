package com.example.demo.service;

import com.example.demo.model.Compra;

import java.util.List;
import java.util.Optional;

public interface CompraService {
    
    public void guardar(Compra compra);
    public Optional<Compra> encontrar(Long id);
    public void eliminar(Long id);
    public List<Compra> listar();
}
