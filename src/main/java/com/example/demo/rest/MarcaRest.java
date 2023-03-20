package com.example.demo.rest;

import com.example.demo.model.Marca;
import com.example.demo.service.MarcaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/marca")
@CrossOrigin(origins = "*")
public class MarcaRest 
{    
    @Autowired
    private MarcaService service;

    @GetMapping(path = "/{id}/productos")
    public ResponseEntity<?> getProductos(@PathVariable Integer id){
    	Marca marca = service.encontrar(id).orElse(null);
    	if (marca == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	
    	return ResponseEntity.ok(marca.getProductoCollection());
    }
    
    @GetMapping(path = "/{id}/cntProductos")
    public ResponseEntity<?> getCntProductos(@PathVariable Integer id){
    	Marca marca = service.encontrar(id).orElse(null);
    	if (marca == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
    	
    	return ResponseEntity.ok(marca.getProductoCollection().size());
    }
    
    @GetMapping
    public ResponseEntity<List<Marca>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id)
    {
        Marca marca = service.encontrar(id).orElse(null);
        if (marca == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(marca);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Marca nuevo, BindingResult br) 
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Marca actual, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Marca nuevo = service.encontrar(actual.getIdMarca()).orElse(null);
        if(nuevo == null) return new ResponseEntity<>("Marca no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(actual);
        return ResponseEntity.ok(service.encontrar(actual.getIdMarca()));
    }
    
    @GetMapping(path = "/{id}/cantidad")
    public ResponseEntity<?> getCantidad(@PathVariable Integer id) 
    {
        Marca marca = service.encontrar(id).orElse(null);
        if (marca == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);

        return ResponseEntity.ok( marca.getProductoCollection().size());
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
        Marca marca = service.encontrar(id).orElse(null);
        if (marca == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(marca);
    }
    
}