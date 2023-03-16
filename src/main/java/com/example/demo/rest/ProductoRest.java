/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.Categoria;
import com.example.demo.model.Producto;
import com.example.demo.negocio.SokaShoes;
import com.example.demo.service.CategoriaService;
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
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
@Slf4j
public class ProductoRest {

    @Autowired
    ProductoService pser;

    @Autowired
    CategoriaService cser;

    @GetMapping
    public ResponseEntity<List<Producto>> getProducto() {
        return ResponseEntity.ok(pser.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Producto p, BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Categoria categoria = cser.encontrar(p.getCategoria().getIdCategoria()).orElse(null);
        if (categoria == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id", "La categoria no existe"), HttpStatus.NOT_FOUND);
        }
        p.setCategoria(categoria);
        pser.guardar(p);
        return ResponseEntity.ok(p);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Producto p, BindingResult br){
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Producto producto = pser.encontrar(p.getIdProducto()).orElse(null);
        if(producto == null){
            return new ResponseEntity("Producto no existe",HttpStatus.NOT_FOUND);
        }
        pser.guardar(p);
        return ResponseEntity.ok(pser.encontrar(p.getIdProducto()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrarProducto(@PathVariable int id) {
        Producto p = pser.encontrar(id).orElse(null);
        if (p == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(p);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable int id) {
        Producto p = pser.encontrar(id).orElse(null);
        if(p == null){
            return new ResponseEntity("El producto no fue encontrado", HttpStatus.NOT_FOUND);
        }
        pser.eliminar(id);
        return ResponseEntity.ok(p);
    }

    @GetMapping(path = "/cantidadDisponible")
    public ResponseEntity<?> getProductosDisponibles() {
        List<Producto> productos = pser.listar();
        int cantidad = 0;
        for (int i = 0; i < productos.size(); i++) {
            cantidad += productos.get(i).getCantidad();
        }
        return ResponseEntity.ok(cantidad);
    }

    @GetMapping(path = "/{id}/deshabilitar")
    public ResponseEntity<?> deshabilitar(@PathVariable int id) {
        Producto p = pser.encontrar(id).orElse(null);
        p.setEstado(false);
        pser.guardar(p);
        return ResponseEntity.ok(p);
    }
    @GetMapping(path = "/{id}/habilitar")
    public ResponseEntity<?> habilitar(@PathVariable int id) {
        Producto p = pser.encontrar(id).orElse(null);
        p.setEstado(true);
        pser.guardar(p);
        return ResponseEntity.ok(p);
    }



}
