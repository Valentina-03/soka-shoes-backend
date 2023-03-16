/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.*;
import com.example.demo.service.MarcaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author Santi & Dani
 */

@RestController
@RequestMapping("/marca")
@CrossOrigin(origins = "*")
public class MarcaRest {
    
    @Autowired
    MarcaService mser;
    
    @GetMapping
    public ResponseEntity<List<Marca>> getMarca() {
        return ResponseEntity.ok(mser.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Marca p, BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        mser.guardar(p);
        return ResponseEntity.ok(p);
    }

    @GetMapping(path = "/{id}/cantidad")
    public ResponseEntity<?> cantidadMarca(@PathVariable int id) {
        Marca marca = mser.encontrar(id).orElse(null);
        if (marca == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok( marca.productoCollection().size());
    }

}
