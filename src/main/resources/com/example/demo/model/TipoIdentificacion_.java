package com.example.demo.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.887-0500")
@StaticMetamodel(TipoIdentificacion.class)
public class TipoIdentificacion_ {
	public static volatile SingularAttribute<TipoIdentificacion, Integer> idTipo;
	public static volatile SingularAttribute<TipoIdentificacion, String> descripcion;
	public static volatile SingularAttribute<TipoIdentificacion, String> tipo;
	public static volatile CollectionAttribute<TipoIdentificacion, Persona> personaCollection;
}
