package com.example.demo.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.security.model.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String nombreUsuario);
    boolean existsByUsername(String nombreUsuario);
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}