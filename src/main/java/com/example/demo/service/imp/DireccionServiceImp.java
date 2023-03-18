package com.example.demo.service.imp;

import com.example.demo.dao.DireccionDAO;
import com.example.demo.model.Direccion;
import com.example.demo.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionServiceImp implements DireccionService {

    @Autowired
    DireccionDAO dDAO;

    @Override
    @Transactional
    public void guardar(Direccion direccion) {
        dDAO.save(direccion);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Direccion> encontrar(int id) { 
    	return dDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Direccion> listar() {
        return dDAO.findAll();
    }

    @Override
    public void eliminar(int id) {
        dDAO.deleteById(id);
    }
    
}
