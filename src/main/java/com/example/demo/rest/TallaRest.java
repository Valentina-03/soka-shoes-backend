package com.example.demo.rest;

import com.example.demo.model.Talla;
import com.example.demo.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/talla")
@CrossOrigin(origins = "*")
public class TallaRest 
{
    @Autowired
    private TallaService service;
    
    @GetMapping(path = "/{id}/productos")
    public ResponseEntity<?> getProductos(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductos(id));
    }
    
    @GetMapping(path = "/{id}/cntProductos")
    public ResponseEntity<?> getCantidad(@PathVariable Integer id){
        return ResponseEntity.ok(service.getProductos(id).size());
    }
    
    @GetMapping
    public ResponseEntity<List<Talla>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id){
        Talla talla = service.encontrar(id).orElse(null);
        if (talla == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(talla);
    }
}