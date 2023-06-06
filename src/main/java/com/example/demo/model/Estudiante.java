package com.example.demo.model;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "estudiante")
public class Estudiante {
	
	@Id
	@Column(name = "codigo")
	private String codigo;
	
	@Column(name = "per_nom")
    private String perNom;
    
    @Column(name = "sdo_nom")
    private String sdoNom;
    
    @Column(name = "per_apell")
    private String perApell;
    
    @Column(name = "sdo_apell")
    private String sdoApell;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "clave")
    private String clave;
	
}