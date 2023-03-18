package com.example.demo.rest;

import com.example.demo.model.Color;
import com.example.demo.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/color")
@CrossOrigin(origins = "*")
public class ColorRest {

    @Autowired
    private ColorService service;

    @GetMapping
    public ResponseEntity<List<Color>> getColor() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable String id) 
    {
        Color color = service.encontrar(id).orElse(null);
        if (color == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);        
        return ResponseEntity.ok(color);
    }

    /*@GetMapping(path = "/{id}/cantidad")
    public ResponseEntity<?> cantidadPorColor(@PathVariable String id){
        Color color = service.encontrar(id).orElse(null);
        if (color == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        int cantidad = color.getProductoCollection().size();
        return ResponseEntity.ok(cantidad);
    }*/
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable String id)
    {
        Color color = service.encontrar(id).orElse(null);
        if (color == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Color nuevo, BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }
    
}