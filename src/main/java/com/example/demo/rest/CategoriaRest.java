package com.example.demo.rest;

import com.example.demo.model.Categoria;
import com.example.demo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaRest 
{
    @Autowired
    private CategoriaService service;
    
    @GetMapping(path = "/{id}/productos")
    public ResponseEntity<?> getProductos(@PathVariable Integer id){
    	Categoria categoria = service.encontrar(id).orElse(null);
    	if (categoria == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	
    	return ResponseEntity.ok(categoria.getProductoCollection());
    }
    
    @GetMapping(path = "/{id}/cntProductos")
    public ResponseEntity<?> getCntProductos(@PathVariable Integer id){
    	Categoria categoria = service.encontrar(id).orElse(null);
    	if (categoria == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	
    	return ResponseEntity.ok(categoria.getProductoCollection().size());
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
        Categoria categoria = service.encontrar(id).orElse(null);
        if (categoria == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Categoria nuevo, BindingResult br) 
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Categoria actual, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Categoria nuevo = service.encontrar(actual.getIdCategoria()).orElse(null);
        if(nuevo == null) return new ResponseEntity<>("Categoría no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(actual);
        return ResponseEntity.ok(service.encontrar(actual.getIdCategoria()));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
        Categoria categoria = service.encontrar(id).orElse(null);
        if (categoria == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(categoria);
    }
}