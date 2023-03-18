package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-17T23:04:02.028-0500")
@StaticMetamodel(Ciudad.class)
public class Ciudad_ {
	public static volatile SingularAttribute<Ciudad, Integer> idDepartamento;
	public static volatile SingularAttribute<Ciudad, String> nombre;
	public static volatile CollectionAttribute<Ciudad, Ciudad> ciudadCollection;
}
