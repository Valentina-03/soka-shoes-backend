package com.example.demo.dao;

import com.example.demo.security.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioDAO extends JpaRepository<Usuario, Integer>{}