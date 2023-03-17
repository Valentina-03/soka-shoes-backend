package com.example.demo.service;

import com.example.demo.model.Transaccionp;
import java.util.List;
import java.util.Map;

public interface TransaccionService {
    
    public void guardar(Transaccionp transaccion);
    public Transaccionp encontrar(String id);
    public List<Transaccionp> listar();
    public void eliminar(String id);
    public Transaccionp getDatos(Map<String, String> body);
    
}
