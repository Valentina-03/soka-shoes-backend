package com.example.demo.rest;

import com.example.demo.model.Departamento;
import com.example.demo.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/departamento")
@CrossOrigin(origins = "*")
public class DepartamentoRest 
{
    @Autowired
    private DepartamentoService service;
    
    
    @GetMapping(path = "/{id}/ciudades")
    public ResponseEntity<?> getCiudades(@PathVariable Integer id)
    {
    	Departamento departamento = service.encontrar(id).orElse(null);
        if (departamento == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	
        return ResponseEntity.ok(departamento.getCiudadCollection());
    }
    
    @GetMapping
    public ResponseEntity<List<Departamento>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
    	Departamento departamento = service.encontrar(id).orElse(null);
        if (departamento == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(departamento);
    }
}