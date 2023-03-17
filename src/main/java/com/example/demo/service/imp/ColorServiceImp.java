package com.example.demo.service.imp;

import com.example.demo.dao.ColorDAO;
import com.example.demo.model.Color;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImp implements ColorService {

    @Autowired
    ColorDAO cDAO;

    @Override
    @Transactional
    public void guardar(Color color) {
        cDAO.save(color);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Color> encontrar(String id) { 
    	return cDAO.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Color> listar() {
        return cDAO.findAll();
    }

    @Override
    public void eliminar(String id) {
        cDAO.deleteById(id);
    }
    
}
