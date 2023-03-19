package com.example.demo.rest;

import com.example.demo.model.Direccion;
import com.example.demo.service.DireccionService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/direccion")
@CrossOrigin(origins = "*")
public class DireccionRest 
{
    @Autowired
    private DireccionService service;
    
    @GetMapping(path = "/{id}/precioenvio")
    public ResponseEntity<?> getPrecioEnvio(@PathVariable Integer id)
    {
        Direccion direccion = service.encontrar(id).orElse(null);
        if (direccion == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(direccion.getPrecioEnvio());
    }
    
    @GetMapping
    public ResponseEntity<List<Direccion>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
        Direccion direccion = service.encontrar(id).orElse(null);
        if (direccion == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(direccion);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Direccion nuevo, BindingResult br) 
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }
    
    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Direccion actual, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Direccion nuevo = service.encontrar(actual.getIdDireccion()).orElse(null);
        if(nuevo == null) return new ResponseEntity<>("Color no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(actual);
        return ResponseEntity.ok(actual);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
    	Direccion direccion = service.encontrar(id).orElse(null);
        if (direccion == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(direccion);
    }    
}