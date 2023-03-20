package com.example.demo.rest;

import com.example.demo.model.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/producto")
@CrossOrigin(origins = "*")
public class ProductoRest 
{
    @Autowired
    private ProductoService service;
    
    
    @GetMapping("/{id}/disponible/{color}/{talla}/{cantidad}")
    public ResponseEntity<?> getDetalle(@PathVariable Integer id, @PathVariable String color, @PathVariable Integer talla, @PathVariable Integer cantidad)
    {
    	return ResponseEntity.ok(service.obtenerDetalle(id, color, talla, cantidad));
    }
    
    @GetMapping
    public ResponseEntity<List<Producto>> getProductosDisponibles(){
    	return ResponseEntity.ok(service.listarDisponibles());
    }
    
    @GetMapping("/{id}/detalles")
    public ResponseEntity<?> getDetalles(@PathVariable Integer id){
    	return ResponseEntity.ok(service.obtenerDetalles(id));
    }
    
    @GetMapping("/{marca}/{categoria}/{color}/{talla}/{precio_min}/{precio_max}")
    public ResponseEntity<?> getProductosFiltro(@PathVariable Integer marca, @PathVariable Integer categoria, @PathVariable String color, @PathVariable Integer talla, @PathVariable Double precio_min, @PathVariable Double precio_max){
    	return ResponseEntity.ok(service.filtrar(marca, categoria, color, talla, precio_min, precio_max));
    }
    
    @GetMapping(path = "/cantidadDisponible")
    public ResponseEntity<?> getTotalProductos() 
    {
        List<Producto> productos = service.listar();
        long cantidad = 0;
        for (int i = 0; i < productos.size(); i++) {
        	if(productos.get(i).getEstado())
        		cantidad += productos.get(i).getCantidad();
        }
        return ResponseEntity.ok(cantidad);
    }

    @GetMapping(path = "/{id}/deshabilitar")
    public ResponseEntity<?> deshabilitar(@PathVariable Integer id) 
    {
        Producto p = service.encontrar(id).orElse(null);
        if(p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        p.setEstado(false);
        service.guardar(p);
        return ResponseEntity.ok(p);
    }
    
    @GetMapping(path = "/{id}/habilitar")
    public ResponseEntity<?> habilitar(@PathVariable Integer id) 
    {
        Producto p = service.encontrar(id).orElse(null);
        if(p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        p.setEstado(true);
        service.guardar(p);
        return ResponseEntity.ok(p);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Producto>> get() {
    	return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody @Valid Producto nuevo, BindingResult br){
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        service.guardar(nuevo);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestBody @Valid Producto p, BindingResult br)
    {
        if (br.hasErrors()) return new ResponseEntity<List<ObjectError>>(br.getAllErrors(), HttpStatus.BAD_REQUEST);
        
        Producto producto = service.encontrar(p.getIdProducto()).orElse(null);
        if(producto == null) return new ResponseEntity<>("Producto no existe",HttpStatus.NOT_FOUND);
        
        p.setUpdatedAt(new Date());
        service.guardar(p);
        return ResponseEntity.ok(service.encontrar(p.getIdProducto()));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> encontrar(@PathVariable Integer id) 
    {
        Producto p = service.encontrar(id).orElse(null);
        if (p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        return ResponseEntity.ok(p);
    }
    

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id)
    {
        Producto p = service.encontrar(id).orElse(null);
        if(p == null) return new ResponseEntity<ObjectError>(new ObjectError("id","No existe el id"), HttpStatus.NOT_FOUND);
        
        service.eliminar(id);
        return ResponseEntity.ok(p);
    }
}