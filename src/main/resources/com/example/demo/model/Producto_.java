package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.878-0500")
@StaticMetamodel(Producto.class)
public class Producto_ {
	public static volatile SingularAttribute<Producto, Integer> idProducto;
	public static volatile SingularAttribute<Producto, Integer> precio;
	public static volatile SingularAttribute<Producto, Integer> cantidad;
	public static volatile SingularAttribute<Producto, String> urlImg;
	public static volatile SingularAttribute<Producto, String> modelo;
	public static volatile SingularAttribute<Producto, String> descripcion;
	public static volatile SingularAttribute<Producto, Boolean> estado;
	public static volatile CollectionAttribute<Producto, DetalleCompra> detalleCompraCollection;
	public static volatile SingularAttribute<Producto, Categoria> categoria;
	public static volatile SingularAttribute<Producto, Marca> marca;
	public static volatile CollectionAttribute<Producto, Color> colorCollection;
	public static volatile CollectionAttribute<Producto, Talla> tallaCollection;
	public static volatile CollectionAttribute<Producto, Carrito> carritoCollection;
}
