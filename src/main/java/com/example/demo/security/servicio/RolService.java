package com.example.demo.security.servicio;

import com.example.demo.security.model.Rol;
import com.example.demo.security.model.Rol.RolNombre;
import com.example.demo.security.dao.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;
    
    @Transactional(readOnly = true)
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }

        
    @Transactional
    public void guardar(Rol rol) {
        rolRepository.save(rol);
    }

    @Transactional
    public void eliminar(int id) {
        rolRepository.deleteById(id);
    }

    @Transactional(readOnly = true )
    public Optional<Rol> encontrar(int id) {
        return rolRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Rol> listar() {
        return rolRepository.findAll();
    }
    
    public void save(Rol rol){
        rolRepository.save(rol);
    }
}

