package com.example.demo.rest;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/persona")
@CrossOrigin(origins = "*")
public class PersonaRest 
{
    @Autowired
    private PersonaService service;

    @GetMapping
    public ResponseEntity<List<Persona>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Persona> eliminar(@PathVariable Integer id) 
    {
        Persona p = service.encontrar(id).orElse(null);
        service.eliminar(id);
        return ResponseEntity.ok(p);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id) 
    {
        Persona p = service.encontrar(id).orElse(null);
        if (p == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Persona p, BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(p);
        return ResponseEntity.ok(p);
    }
    
}