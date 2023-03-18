package com.example.demo.rest;

import com.example.demo.model.DetalleProducto;
import com.example.demo.service.DetalleProductoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detproducto")
@CrossOrigin(origins = "*")
public class DetalleProductoRest 
{
    @Autowired
    private DetalleProductoService service;
    
    @GetMapping
    public ResponseEntity<List<DetalleProducto>> getDetalleColor() {
        return ResponseEntity.ok(service.listar());
    }
    
}