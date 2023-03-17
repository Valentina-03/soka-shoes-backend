package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.885-0500")
@StaticMetamodel(Talla.class)
public class Talla_ {
	public static volatile SingularAttribute<Talla, Integer> idTalla;
	public static volatile SingularAttribute<Talla, String> nombre;
	public static volatile SingularAttribute<Talla, Short> numero;
	public static volatile CollectionAttribute<Talla, Producto> productoCollection;
}
