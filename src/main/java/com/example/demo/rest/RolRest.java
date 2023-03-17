package com.example.demo.rest;

import com.example.demo.security.model.Rol;
import com.example.demo.security.servicio.RolService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rol")
@CrossOrigin(origins = "*")
public class RolRest 
{    
    @Autowired
    private RolService service;
    
    @GetMapping
    public ResponseEntity<List<Rol>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Rol> eliminar(@PathVariable Integer id) 
    {
        Rol r = service.encontrar(id).orElse(null);
        service.eliminar(id);
        return ResponseEntity.ok(r);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id) 
    {
        Rol r = service.encontrar(id).orElse(null);
        if (r == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(r);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Rol r, BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.save(r);
        return ResponseEntity.ok(r);
    }
    
}