package com.example.demo.model;

import java.util.List;

public class ProductoDetallesRequest 
{
	private Producto producto;
    private List<ProductoDeatallesDto> detalles;
    
    public Producto getProducto() {
        return producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public List<ProductoDeatallesDto> getDetalles() {
        return detalles;
    }
    
    public void setDetalles(List<ProductoDeatallesDto> detalles) {
        this.detalles = detalles;
    }

}
