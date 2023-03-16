/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.Color;
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
@RequestMapping("/color")
@CrossOrigin(origins = "*")
@Slf4j
public class ColorRest {

    @Autowired
    ColorService cser;

    @GetMapping
    public ResponseEntity<List<Color>> getColor() {
        return ResponseEntity.ok(cser.listar());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminarColor(@PathVariable String id) {
        Color color = cser.encontrar(id).orElse(null);
        if (color == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        cser.eliminar(id);
        return ResponseEntity.ok(color);
    }

    @GetMapping(path = "/{id}/cantidad")
    public ResponseEntity<Integer> cantidadPorColor(@PathVariable String id){
        Color color = cser.encontrar(id).orElse(null);
        if (color == null) {
            //return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        int cantidad = color.productoCollection().size();
        return ResponseEntity.ok(cantidad);
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrarColor(@PathVariable String id) {
        Color color = cser.encontrar(id).orElse(null);
        if (color == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(color);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Color c, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        cser.guardar(c);
        return ResponseEntity.ok(c);
    }



}
