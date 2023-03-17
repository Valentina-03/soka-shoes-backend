package com.example.demo.rest;


import com.example.demo.model.Compra;
import com.example.demo.model.Transaccionp;

import java.util.Map;

import com.example.demo.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.TransaccionService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pagos", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class TransaccionRest 
{
    @Autowired
    private TransaccionService service;

    @Autowired
    private CompraService compra_service;
    
    @GetMapping
    public ResponseEntity<List<Transaccionp>> get() {
    	return ResponseEntity.ok(service.listar());
    }

    @PostMapping("/confirmacion")
    public ResponseEntity<?> pagoConfirmado(@RequestParam Map<String, String> body) 
    {
        Transaccionp pay = service.getDatos(body);        
        Compra compra = compra_service.encontrar(Long.parseLong(body.get("reference_sale"))).get();
        
        if (pay.getResponseMessagePol().equals("APPROVED") && pay.getValue() == (long) compra.getTotalCompra()) {
            if (pay.getValue() == (long) compra.getTotalCompra()) {
                pay.setReferenceSale(compra);
                compra.setEstado("APROBADA");
                compra_service.guardar(compra);
            } else 
                compra_service.eliminar(compra.getIdCompra());
        }

        service.guardar(pay);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

}