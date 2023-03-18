package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-17T23:10:01.395-0500")
@StaticMetamodel(Direccion.class)
public class Direccion_ {
	public static volatile SingularAttribute<Direccion, Integer> idCiudad;
	public static volatile SingularAttribute<Direccion, String> nombre;
	public static volatile SingularAttribute<Direccion, Double> precioEnvio;
	public static volatile SingularAttribute<Direccion, Departamento> departamento;
}
