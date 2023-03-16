package com.example.demo.service.imp;

import com.example.demo.dao.CompraDAO;
import com.example.demo.model.Compra;
import com.example.demo.service.CompraService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CompraServiceImp implements CompraService {

    @Autowired
    CompraDAO cDAO;
    
    
    @Override
    @Transactional
    public void guardar(Compra compra) {
        cDAO.save(compra);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        cDAO.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Compra> encontrar(Long id) {
        return cDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Compra> listar() {
        return cDAO.findAll();
    }


    
}
