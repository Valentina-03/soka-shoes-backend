package com.example.demo.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.security.model.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String nombreUsuario);
    boolean existsByUsername(String nombreUsuario);
     Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}