package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class ProductoDeatllesDto 
{
	private Integer idDetalle;
	private Color color;
	private String url;
	private List<Talla> tallas;
	
	public ProductoDeatllesDto() {
		this.tallas = new ArrayList<>();
	}
	
	public ProductoDeatllesDto(Integer idDetalle, Color color, String url, List<Talla> tallas) {
		this.idDetalle = idDetalle;
		this.color = color;
		this.url = url;
		this.tallas = tallas;
	}
	
	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProductoDeatllesDto))
            return false;
		ProductoDeatllesDto other = (ProductoDeatllesDto) object;
		if ((this.idDetalle == null && other.idDetalle != null) || (this.idDetalle != null && other.idDetalle == null))
            return false;
        return this.color.getIdColor().equals(other.getColor().getIdColor());
	}
	
	public void addTalla(Talla nuevo) {
		this.tallas.add(nuevo);
	}

	public Integer getIdDetalle() {
		return idDetalle;
	}
	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
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
	public List<Talla> getTallas() {
		return tallas;
	}
	public void setTallas(List<Talla> tallas) {
		this.tallas = tallas;
	}	
}
