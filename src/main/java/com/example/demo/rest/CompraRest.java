package com.example.demo.rest;

import com.example.demo.model.Compra;
import com.example.demo.model.Transaccionp;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.CompraService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/compra")
@CrossOrigin(origins = "*")
public class CompraRest {
    
    @Autowired
    private CompraService service;

    @Autowired
    private UsuarioService user_service;

    @GetMapping
    public ResponseEntity<List<Compra>> getCompra() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping(path = "/{idUsuario}")
    public ResponseEntity<?> guardar(@RequestBody @Valid Compra c, BindingResult br,@PathVariable Integer idUsuario) 
    {
        if (br.hasErrors())
            return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = user_service.encontrar(idUsuario).get();
        c.setUsuario(usuario);
        service.guardar(c);
        return ResponseEntity.ok(c);
    }

    @GetMapping(path = "/{id}/transacciones")
    @SuppressWarnings("unchecked")
    public ResponseEntity<List<Transaccionp>> transaccionPorCompra(@PathVariable Long id) 
    {
    	return ResponseEntity.ok((List<Transaccionp>)service.encontrar(id).get());
    }

    @GetMapping(path = "/preciototal")
    public ResponseEntity<?> getPrecioPorCompra() 
    {
        List<Compra> compras = service.listar();
        int cantidad = 0;
        for (int i = 0; i < compras.size(); i++)
            if(compras.get(i).getEstado().equals("APROBADO"))
                cantidad += compras.get(i).getTotalCompra();
        
        return ResponseEntity.ok(cantidad);
    }
    
}