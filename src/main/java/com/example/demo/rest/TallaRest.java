/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest;

import com.example.demo.model.Marca;
import com.example.demo.model.Talla;
import com.example.demo.model.Talla;
import com.example.demo.model.Talla;
import com.example.demo.service.TallaService;
import com.example.demo.service.TallaService;
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
@RequestMapping("/talla")
@CrossOrigin(origins = "*")
@Slf4j
public class TallaRest {

    @Autowired
    TallaService tallaService;

    @GetMapping
    public ResponseEntity<List<Talla>> getTalla() {
        return ResponseEntity.ok(tallaService.listar());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrarTalla(@PathVariable int id) {
        Talla Talla = tallaService.encontrar(id).orElse(null);
        if (Talla == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(Talla);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Talla> eliminarTalla(@PathVariable int id) {

        Talla talla= tallaService.encontrar(id).orElse(null);

        tallaService.eliminar(id);

        return ResponseEntity.ok(talla);
    }


    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Talla a, BindingResult br) {

        if (br.hasErrors()) {
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        tallaService.guardar(a);
        return ResponseEntity.ok(a);
    }

    @GetMapping(path = "/{id}/cantidad")
    public ResponseEntity<?> cantidadPorTalla(@PathVariable int id){
        Talla talla = tallaService.encontrar(id).orElse(null);
        if (talla == null) {
            return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(talla.productoCollection().size());
    }
    
}
