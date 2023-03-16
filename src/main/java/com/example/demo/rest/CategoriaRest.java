/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.Categoria;
import com.example.demo.model.Color;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ColorService;
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
@RequestMapping("/categoria")
@CrossOrigin(origins = "*")
@Slf4j
public class CategoriaRest {

    @Autowired
    CategoriaService cser;

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategoria() {
        return ResponseEntity.ok(cser.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarCategoria(@PathVariable int id) {
        Categoria categoria = cser.encontrar(id).orElse(null);
        if (categoria == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        cser.eliminar(id);
        return ResponseEntity.ok(categoria);
    }
    
    @GetMapping(path = "/{id}")
        public ResponseEntity<?> encontrarCategoria(@PathVariable int id) {
        Categoria categoria = cser.encontrar(id).orElse(null);
        if (categoria == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Categoria c, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        cser.guardar(c);
        return ResponseEntity.ok(c);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Categoria c, BindingResult br){
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        Categoria categoria = cser.encontrar(c.getIdCategoria()).orElse(null);
        if(categoria ==null){
            return new ResponseEntity("Evento no existe",HttpStatus.NOT_FOUND);
        }
        cser.guardar(c);

        return ResponseEntity.ok(cser.encontrar(c.getIdCategoria()));
    }

}
