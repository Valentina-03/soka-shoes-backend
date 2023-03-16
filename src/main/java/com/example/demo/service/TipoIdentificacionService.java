package com.example.demo.service;

import com.example.demo.model.TipoIdentificacion;
import java.util.List;
import java.util.Optional;

public interface TipoIdentificacionService {
    
    public void guardar(TipoIdentificacion tipoIdentificacion);
    public void eliminar(int id);
    public Optional<TipoIdentificacion> encontrar(int id);
    public List<TipoIdentificacion> listar();
            
}
