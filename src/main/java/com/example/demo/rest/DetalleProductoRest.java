package com.example.demo.rest;

import com.example.demo.model.DetalleProducto;
import com.example.demo.service.DetalleProductoService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/detproducto")
@CrossOrigin(origins = "*")
public class DetalleProductoRest 
{
    @Autowired
    private DetalleProductoService service;
    
    @GetMapping(path = "/{idProducto}/{idColor}/{idTalla}")
    public ResponseEntity<?> getByAll(@PathVariable Integer idProducto, @PathVariable String idColor, @PathVariable Integer idTalla){
    	return ResponseEntity.ok(service.listarProductosPorTodo(idProducto, idColor, idTalla));
    }
    
    @GetMapping
    public ResponseEntity<List<DetalleProducto>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id){
        DetalleProducto detalle = service.encontrar(id).orElse(null);
        if (detalle == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(detalle);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
    	DetalleProducto detalle = service.encontrar(id).orElse(null);
        if (detalle == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(detalle);
    }  
    
    /*@GetMapping(path = "/p-c/{idProducto}/{idColor}")
    public ResponseEntity<?> getByProdCol(@PathVariable Integer idProducto, @PathVariable String idColor){
    	return ResponseEntity.ok(service.listarProductosPorProdCol(idProducto, idColor));
    }
    
    @GetMapping(path = "/p-t/{idProducto}/{idTalla}")
    public ResponseEntity<?> getByProdTal(@PathVariable Integer idProducto, @PathVariable Integer idTalla){
    	return ResponseEntity.ok(service.listarProductosPorProdTal(idProducto, idTalla));
    }
    
    @GetMapping(path = "/c-t/{idColor}/{idTalla}")
    public ResponseEntity<?> getByColTal(@PathVariable String idColor, @PathVariable Integer idTalla){
    	return ResponseEntity.ok(service.listarProductosPorColTal(idColor, idTalla));
    }*/
}