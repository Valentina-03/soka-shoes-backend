package com.example.demo.rest;

import com.example.demo.model.Direccion;
import com.example.demo.service.DireccionService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/direccion")
@CrossOrigin(origins = "*")
public class DireccionRest 
{
    @Autowired
    private DireccionService service;
    
    @GetMapping
    public ResponseEntity<List<Direccion>> getDireccion() {
        return ResponseEntity.ok(service.listar());
    }
    
}