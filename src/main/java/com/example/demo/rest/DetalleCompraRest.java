package com.example.demo.rest;

import com.example.demo.model.DetalleCompra;
import com.example.demo.service.DetalleCompraService;
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
@RequestMapping("/api/detcompra")
@CrossOrigin(origins = "*")
public class DetalleCompraRest 
{
    @Autowired
    private DetalleCompraService service;
    
    @GetMapping
    public ResponseEntity<List<DetalleCompra>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
        DetalleCompra detalle = service.encontrar(id).orElse(null);
        if (detalle == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid DetalleCompra nuevo, BindingResult br) 
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }
    
    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid DetalleCompra actual, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        DetalleCompra nuevo = service.encontrar(actual.getIdDetalle()).orElse(null);
        if(nuevo == null) return new ResponseEntity<>("DetalleCompra no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(actual);
        return ResponseEntity.ok(actual);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
    	DetalleCompra detalle = service.encontrar(id).orElse(null);
        if (detalle == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(detalle);
    }    
}