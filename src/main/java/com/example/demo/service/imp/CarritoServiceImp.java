package com.example.demo.service.imp;

import com.example.demo.dao.CarritoDAO;
import com.example.demo.model.Carrito;
import com.example.demo.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoServiceImp implements CarritoService {

    @Autowired
    CarritoDAO cDAO;

    @Override
    @Transactional
    public void guardar(Carrito carrito) {
        cDAO.save(carrito);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Carrito> encontrar(Long id) { 
    	return cDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Carrito> listar() {
        return cDAO.findAll();
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cDAO.deleteById(id);
    }   
}