package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-17T22:59:22.643-0500")
@StaticMetamodel(Departamento.class)
public class Departamento_ {
	public static volatile SingularAttribute<Departamento, Integer> idCategoria;
	public static volatile SingularAttribute<Departamento, String> nombre;
	public static volatile SingularAttribute<Departamento, String> descripcion;
	public static volatile CollectionAttribute<Departamento, Producto> productoCollection;
}
