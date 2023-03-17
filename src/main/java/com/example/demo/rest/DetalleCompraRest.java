package com.example.demo.rest;

import com.example.demo.model.DetalleCompra;
import com.example.demo.service.DetalleCompraService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detcompra")
@CrossOrigin(origins = "*")
public class DetalleCompraRest 
{
    @Autowired
    private DetalleCompraService service;
    
    @GetMapping
    public ResponseEntity<List<DetalleCompra>> getDetalleCompra() {
        return ResponseEntity.ok(service.listar());
    }
    
}