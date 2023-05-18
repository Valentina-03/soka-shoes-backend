package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin(origins = "*")
public class CarritoRest 
{
    @Autowired
    private CarritoService service;

    @Autowired
    private UsuarioService user_service;
    

    @GetMapping
    public ResponseEntity<List<Carrito>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Long id) 
    {
	    Carrito carrito = service.encontrar(id).orElse(null);
	    if (carrito == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
	    
	    return ResponseEntity.ok(carrito);
	}

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Carrito c, BindingResult br) 
    {
    	if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        service.guardar(c);
        return ResponseEntity.ok(c);
    }
    
    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Carrito c, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Carrito carrito = service.encontrar(c.getIdCarrito()).orElse(null);
        if(carrito == null) return new ResponseEntity<>("Carrito no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(c);
        return ResponseEntity.ok(service.encontrar(c.getIdCarrito()));
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) 
    {
        Carrito carrito = service.encontrar(id).orElse(null);
        if (carrito == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);
        return ResponseEntity.ok(carrito);
    }

    @PatchMapping(path = "/grupo")
    public ResponseEntity<?> guardarTodos(@RequestBody @Valid Carrito c[], BindingResult br) 
    {
    	if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        Usuario u = user_service.encontrar(c[0].getUsuario().getIdUsuario()).orElse(null);
        if(u == null) return new ResponseEntity<>("Usuario no existente", HttpStatus.NOT_FOUND);
        
        Collection<Carrito> uc = u.getCarritoCollection();
        for(Carrito i: c) {
        	if(i.getUsuario().getIdUsuario() != u.getIdUsuario()) return new ResponseEntity<>("Carritos de diferentes usuarios", HttpStatus.BAD_REQUEST);
        	service.guardar(i);
            uc.add(i);                
        }
        
        u.setCarritoCollection(uc);
        user_service.guardar(u);            
        return ResponseEntity.ok(c);
    }
    
    @PatchMapping(path = "/grupo/eliminar")
    public ResponseEntity<?> eliminarTodos(@RequestBody @Valid Carrito c[], BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        for(Carrito carrito: c) {
        	Carrito x = service.encontrar(carrito.getIdCarrito()).orElse(null);
        	if(x == null) continue;
            service.eliminar(x.getIdCarrito());
        }

        return ResponseEntity.ok(c);
    }
}