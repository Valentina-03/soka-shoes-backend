package com.example.demo.model;

import com.example.demo.security.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2023-03-16T19:58:19.875-0500")
@StaticMetamodel(Persona.class)
public class Persona_ {
	public static volatile SingularAttribute<Persona, Integer> idPersona;
	public static volatile SingularAttribute<Persona, String> perNom;
	public static volatile SingularAttribute<Persona, String> sdoNom;
	public static volatile SingularAttribute<Persona, String> perApell;
	public static volatile SingularAttribute<Persona, String> sdoApell;
	public static volatile SingularAttribute<Persona, Short> genero;
	public static volatile SingularAttribute<Persona, Date> fechaNac;
	public static volatile SingularAttribute<Persona, String> cel;
	public static volatile SingularAttribute<Persona, String> correo;
	public static volatile SingularAttribute<Persona, String> dir;
	public static volatile SingularAttribute<Persona, TipoIdentificacion> idtipoId;
	public static volatile SingularAttribute<Persona, Usuario> usuario;
}
