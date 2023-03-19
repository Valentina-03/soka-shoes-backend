package com.example.demo.service;

import com.example.demo.model.Color;
import com.example.demo.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ColorService 
{        
    public void guardar(Color color);
    public void eliminar(String id);
    public Optional<Color> encontrar(String id);
    public List<Color> listar();
    public List<Producto> getProductos(String id);
    public int getCntProductos(String id);
}
