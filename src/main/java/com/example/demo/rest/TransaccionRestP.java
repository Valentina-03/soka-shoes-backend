package com.example.demo.rest;

import com.example.demo.model.Transaccionp;
import com.example.demo.service.TransaccionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/transaccion")
public class TransaccionRestP 
{
    @Autowired
    private TransaccionService tser;
    
    @GetMapping
    public ResponseEntity<List<Transaccionp>> get(){
       return ResponseEntity.ok(tser.listar());
    }
}