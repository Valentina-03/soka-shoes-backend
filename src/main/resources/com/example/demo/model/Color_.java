package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.861-0500")
@StaticMetamodel(Color.class)
public class Color_ {
	public static volatile SingularAttribute<Color, String> idColor;
	public static volatile SingularAttribute<Color, String> nombre;
	public static volatile CollectionAttribute<Color, Producto> productoCollection;
}
