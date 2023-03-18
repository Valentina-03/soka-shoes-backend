package com.example.demo.rest;

import com.example.demo.model.Talla;
import com.example.demo.service.TallaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/talla")
@CrossOrigin(origins = "*")
public class TallaRest 
{
    @Autowired
    private TallaService service;
    
    @GetMapping
    public ResponseEntity<List<Talla>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
        Talla talla = service.encontrar(id).orElse(null);
        if (talla == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(talla);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
        Talla talla = service.encontrar(id).orElse(null);
        if (talla == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(talla);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Talla nuevo, BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    /*@GetMapping(path = "/{id}/cantidad")
    public ResponseEntity<?> cantidadPorTalla(@PathVariable Integer id){
        Talla talla = service.encontrar(id).orElse(null);
        if (talla == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(talla.productoCollection().size());
    }   */ 
    
}