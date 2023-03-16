package com.example.demo.service.imp;

import com.example.demo.dao.TallaDAO;
import com.example.demo.model.Talla;
import com.example.demo.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TallaServiceImp implements TallaService {

    @Autowired
    TallaDAO tDAO;

    @Override
    @Transactional
    public void guardar(Talla talla) {
        tDAO.save(talla);
    }

    @Override
    @Transactional(readOnly = true )
    public Optional<Talla> encontrar(int id) { return tDAO.findById(id);}

    @Override
    @Transactional(readOnly = true)
    public List<Talla> listar() {
        return tDAO.findAll();
    }

    @Override
    public void eliminar(int id) {
        tDAO.deleteById(id);
    }

}
