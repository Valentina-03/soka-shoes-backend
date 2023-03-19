package com.example.demo.service.imp;

import com.example.demo.dao.DepartamentoDAO;
import com.example.demo.model.Departamento;
import com.example.demo.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoServiceImp implements DepartamentoService {

    @Autowired
    DepartamentoDAO dDAO;

    @Override
    @Transactional
    public void guardar(Departamento direccion) {
        dDAO.save(direccion);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Departamento> encontrar(Integer id) { 
    	return dDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> listar() {
        return dDAO.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        dDAO.deleteById(id);
    }    
}