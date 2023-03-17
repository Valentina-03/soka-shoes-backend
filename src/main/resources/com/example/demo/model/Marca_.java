package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.872-0500")
@StaticMetamodel(Marca.class)
public class Marca_ {
	public static volatile SingularAttribute<Marca, Integer> idMarca;
	public static volatile SingularAttribute<Marca, String> nombre;
	public static volatile CollectionAttribute<Marca, Producto> productoCollection;
}
