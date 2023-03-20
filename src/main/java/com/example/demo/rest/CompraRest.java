package com.example.demo.rest;

import com.example.demo.model.Carrito;
import com.example.demo.model.Compra;
import com.example.demo.model.DetalleCompra;
import com.example.demo.model.Direccion;
import com.example.demo.security.model.Usuario;
import com.example.demo.security.servicio.UsuarioService;
import com.example.demo.service.CarritoService;
import com.example.demo.service.CompraService;
import com.example.demo.service.DetalleCompraService;
import com.example.demo.service.DetalleProductoService;
import com.example.demo.service.DireccionService;
import com.example.demo.service.ProductoService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/compra")
@CrossOrigin(origins = "*")
public class CompraRest {
    
    @Autowired
    private CompraService service;
    @Autowired
    private UsuarioService user_service;
    @Autowired
    private ProductoService producto_service;
    @Autowired
    private DireccionService direccion_service;    
    @Autowired
    private DetalleCompraService detalle_service;    
    @Autowired
    private DetalleProductoService detallep_service;
    
    @Autowired
    private CarritoService carrito_service;
    
    
    @GetMapping(path = "/{id}/transacciones")
    public ResponseEntity<?> transaccionPorCompra(@PathVariable Long id)
    {
    	Compra c = service.encontrar(id).orElse(null);        
        if(c == null) return new ResponseEntity<>("Compra no existente", HttpStatus.NOT_FOUND);
    	
        return ResponseEntity.ok(c.getTransaccionpCollection());
    }

    @GetMapping(path = "/total")
    public ResponseEntity<?> getTotalVentas() 
    {
        List<Compra> compras = service.listar();
        double cantidad = 0;
        for (int i = 0; i < compras.size(); i++)
            if(compras.get(i).getEstado().equals("APROBADO"))
                cantidad += compras.get(i).getTotalCompra();
        
        return ResponseEntity.ok(cantidad);
    }

    @GetMapping
    public ResponseEntity<List<Compra>> get() {
        return ResponseEntity.ok(service.listar());
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Long id) 
    {
        Compra nuevo = service.encontrar(id).orElse(null);        
        if(nuevo == null) return new ResponseEntity<>("Compra no existente", HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(nuevo);
    } 

    @GetMapping(path = "/{idUsuario}/{direccion}")
    public ResponseEntity<?> guardar(@PathVariable Integer idUsuario, @PathVariable Integer direccion) 
    {
        Usuario u = user_service.encontrar(idUsuario).orElse(null);
        if(u == null) return new ResponseEntity<>("Usuario no existente", HttpStatus.NOT_FOUND);
        Direccion d = direccion_service.encontrar(direccion).orElse(null);
        if(d == null) return new ResponseEntity<>("Direccion no existente", HttpStatus.NOT_FOUND);
        List<DetalleCompra> aux = detalle_service.obtenerPorCarrito(u.getCarritoCollection());
        if(aux == null) return new ResponseEntity<>("Uno o más productos no disponibles", HttpStatus.INTERNAL_SERVER_ERROR);
        
        Compra c = new Compra();
        c.setEstado("PENDIENTE");
        c.setDireccion(d);
        c.setUsuario(u);
        c.setTotalCompra(0d);
        c.setTransaccionpCollection(new ArrayList<>());
        service.guardar(c);
        
        List<DetalleCompra> detalles = new ArrayList<>();        
        for(DetalleCompra i : aux) {        	
        	i.setCompra(c);
        	i.getDetalleProducto().setCantidad(i.getDetalleProducto().getCantidad()-i.getCantidad());
        	i.getDetalleProducto().getProducto().setCantidad(i.getDetalleProducto().getProducto().getCantidad()-i.getCantidad());
        	producto_service.guardar(i.getDetalleProducto().getProducto());
        	detallep_service.guardar(i.getDetalleProducto());
        	detalle_service.guardar(i);
        	c.setTotalCompra(c.getTotalCompra()+i.getPrecioTot());
        	detalles.add(i);
        }
        for(Carrito i: u.getCarritoCollection())
        	carrito_service.eliminar(i.getIdCarrito());
        
        c.setDetalleCompraCollection(detalles);
        service.guardar(c);  
        return ResponseEntity.ok(c);
    }  
    
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> editar(@RequestBody @Valid Compra actual, BindingResult br, @PathVariable Long id) 
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        Compra nuevo = service.encontrar(id).orElse(null);        
        if(nuevo == null) return new ResponseEntity<>("Compra no existente", HttpStatus.NOT_FOUND);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }  
}