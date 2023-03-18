package com.example.demo.service.imp;

import com.example.demo.dao.DetalleProductoDAO;
import com.example.demo.model.DetalleProducto;
import com.example.demo.service.DetalleProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetalleProductoImp implements DetalleProductoService{
    
    @Autowired
    DetalleProductoDAO dpDAO;

    @Override
    @Transactional
    public void guardar(DetalleProducto detalleProducto) {
        dpDAO.save(detalleProducto);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<DetalleProducto> encontrar(int id) {
        return dpDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DetalleProducto> listar() {
        return dpDAO.findAll();
    }
    
}