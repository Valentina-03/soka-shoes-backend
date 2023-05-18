package com.example.demo.security.dao;

import com.example.demo.security.model.Rol;
import com.example.demo.security.model.Rol.RolNombre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}