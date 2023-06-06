package com.example.demo.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_actividad")
public class TipoActividad {

	@Id
	@Column(name = "id_tipo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@OneToMany(mappedBy = "tipoActividad")
    @JsonIgnore
    private Collection<Actividad> actividades;
	
}