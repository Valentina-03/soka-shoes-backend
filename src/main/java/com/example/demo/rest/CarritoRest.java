package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.CarritoService;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    private ProductoService producto_service;

    @GetMapping
    public ResponseEntity<List<Carrito>> get() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) 
    {
        Carrito carrito = service.encontrar(id).orElse(null);
        if (carrito == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);
        return ResponseEntity.ok(carrito);
    }

    @GetMapping(path = "/{id}")
        public ResponseEntity<?> encontrarCarrito(@PathVariable Integer id) 
    {
        Carrito carrito = service.encontrar(id).orElse(null);
        if (carrito == null)
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Carrito c, BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Usuario u = user_service.encontrar(c.getUsuario().getId_Usuario()).orElse(null);

        for(Carrito carrito: u.carritoCollection())
            if(carrito.getProducto().getIdProducto() == c.getProducto().getIdProducto()){
                return ResponseEntity.ok(c);
        }

        c.setProducto(this.producto_service.encontrar(c.getProducto().getIdProducto()).get());
        service.guardar(c);
        return ResponseEntity.ok(c);
    }

    @PatchMapping(path = "/grupo")
    public ResponseEntity<?> guardarTodos(@RequestBody @Valid Carrito c[], BindingResult br) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        for(Carrito carrito: c)
            service.guardar(carrito);

        return ResponseEntity.ok(c);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Carrito c, BindingResult br)
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Carrito carrito = service.encontrar(c.getIdCarrito()).orElse(null);
        if(carrito == null)
        	return new ResponseEntity<>("Carrito no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(c);
        return ResponseEntity.ok(service.encontrar(c.getIdCarrito()));
    }

}