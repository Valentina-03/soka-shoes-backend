package com.example.demo.service;

import com.example.demo.model.Producto;
import com.example.demo.model.Talla;

import java.util.List;
import java.util.Optional;

public interface TallaService 
{        
    public void guardar(Talla talla);
    public void eliminar(Integer id);
    public Optional<Talla> encontrar(Integer id);
    public List<Talla> listar();
    public List<Producto> getProductos(Integer id);
    public int getCntProductos(Integer id);
}
