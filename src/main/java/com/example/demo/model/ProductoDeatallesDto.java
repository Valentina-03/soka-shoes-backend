package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.util.Pair;

public class ProductoDeatallesDto 
{
	private Integer idProducto;
	private Color color;
	private String url;
	private List<Pair<Talla, Integer>> tallas;
	private Integer cantidad;
	
	public ProductoDeatallesDto() {
		this.tallas = new ArrayList<>();
		this.cantidad = 0;
	}
	
	public ProductoDeatallesDto(Integer idProducto, Color color, String url, List<Pair<Talla, Integer>> tallas, Integer cantidad) {
		this.idProducto = idProducto;
		this.color = color;
		this.url = url;
		this.tallas = tallas;
		this.cantidad = cantidad;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProductoDeatallesDto))
            return false;
		ProductoDeatallesDto other = (ProductoDeatallesDto) object;
		if ((this.idProducto == null && other.idProducto != null) || (this.idProducto != null && other.idProducto == null))
            return false;
        return this.color.getIdColor().equals(other.getColor().getIdColor());
	}
	
	public void addTalla(Talla talla, Integer cantidad) {
		this.cantidad += cantidad;
		this.tallas.add(Pair.of(talla, cantidad));
	}

	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Pair<Talla, Integer>> getTallas() {
		return tallas;
	}
	public void setTallas(List<Pair<Talla, Integer>> tallas) {
		this.tallas = tallas;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
