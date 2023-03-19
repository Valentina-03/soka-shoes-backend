package com.example.demo.service;

import com.example.demo.model.Departamento;

import java.util.List;
import java.util.Optional;

public interface DepartamentoService 
{        
    public void guardar(Departamento departamento);
    public void eliminar(Integer id);
    public Optional<Departamento> encontrar(Integer id);
    public List<Departamento> listar();
}
