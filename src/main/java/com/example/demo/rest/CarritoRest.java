/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.model.Categoria;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.CarritoService;
import com.example.demo.service.ProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *
 * @author GenesisDanielaVJ
 */
@RestController
@RequestMapping("/carrito")
@CrossOrigin(origins = "*")
@Slf4j
public class CarritoRest {

    @Autowired
    CarritoService cser;

    @Autowired
    UsuarioService usu;

    @Autowired
    ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Carrito>> getCarrito() {
        return ResponseEntity.ok(cser.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarCarrito(@PathVariable int id) {
        Carrito carrito = cser.encontrar(id).orElse(null);
        if (carrito == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        cser.eliminar(id);
        return ResponseEntity.ok(carrito);
    }

    @GetMapping(path = "/{id}")
        public ResponseEntity<?> encontrarCarrito(@PathVariable int id) {
        Carrito carrito = cser.encontrar(id).orElse(null);
        if (carrito == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(carrito);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Carrito c, BindingResult br) {
        if (br.hasErrors()) {

            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Usuario u = usu.encontrar(c.getUsuario().getId_Usuario()).orElse(null);

        for(Carrito carrito: u.carritoCollection()){
            if(carrito.getProducto().getIdProducto() == c.getProducto().getIdProducto()){
                return ResponseEntity.ok(c);
            }
        }

        c.setProducto(this.productoService.encontrar(c.getProducto().getIdProducto()).get());
        cser.guardar(c);
        return ResponseEntity.ok(c);
    }

    @PatchMapping(path = "/grupo")
    public ResponseEntity<?> guardarTodos(@RequestBody @Valid Carrito c[], BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        for(Carrito carrito: c)
            cser.guardar(carrito);

        return ResponseEntity.ok(c);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Carrito c, BindingResult br){
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Carrito carrito = cser.encontrar(c.getIdCarrito()).orElse(null);
        if(carrito ==null){
            return new ResponseEntity("Evento no existe",HttpStatus.NOT_FOUND);
        }
        cser.guardar(c);

        return ResponseEntity.ok(cser.encontrar(c.getIdCarrito()));
    }

}
