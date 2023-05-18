package com.example.demo.rest;

import com.example.demo.model.Color;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/color")
@CrossOrigin(origins = "*")
public class ColorRest {

    @Autowired
    private ColorService service;

    @GetMapping(path = "/{id}/cntProductos")
    public ResponseEntity<?> getCantidad(@PathVariable String id){
        return ResponseEntity.ok(service.getProductos(id).size());
    }
    
    @GetMapping(path = "/{id}/productos")
    public ResponseEntity<?> getProductos(@PathVariable String id){
        return ResponseEntity.ok(service.getProductos(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Color>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable String id){
        Color color = service.encontrar(id).orElse(null);
        if (color == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(color);
    }
}