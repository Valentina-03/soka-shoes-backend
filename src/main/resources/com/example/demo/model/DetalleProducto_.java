package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-18T15:28:13.269-0500")
@StaticMetamodel(DetalleProducto.class)
public class DetalleProducto_ {
	public static volatile SingularAttribute<DetalleProducto, String> idDetalle;
	public static volatile SingularAttribute<DetalleProducto, String> img;
	public static volatile SingularAttribute<DetalleProducto, Color> color;
	public static volatile SingularAttribute<DetalleProducto, Producto> producto;
}
