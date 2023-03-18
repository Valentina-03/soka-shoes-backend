package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-18T12:06:52.711-0500")
@StaticMetamodel(DetalleColor.class)
public class DetalleColor_ {
	public static volatile SingularAttribute<DetalleColor, String> idColor;
	public static volatile SingularAttribute<DetalleColor, String> nombre;
	public static volatile CollectionAttribute<DetalleColor, Producto> productoCollection;
}
