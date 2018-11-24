package com.escolar.persona.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="MORAL")
public class PersonaMoralDto implements Serializable {
	
	
    private static final long serialVersionUID = 1l;
	
	@Id
	Long idPersona;
		
	String nombre;	
	String razonSocial;		
	Date fechaConstitucion;
	
	@OneToOne   
	@JoinColumn(name = "id_persona")
	private PersonaDto persona;

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}



	public Date getFechaConstitucion() {
		return fechaConstitucion;
	}

	public void setFechaConstitucion(Date fechaConstitucion) {
		this.fechaConstitucion = fechaConstitucion;
	}

	public PersonaDto getPersona() {
		return persona;
	}

	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "PersonaMoralDto [idPersona=" + idPersona + ", nombre=" + nombre + ", razonSocial=" + razonSocial
				+ ", fechaConstitucion=" + fechaConstitucion + ", persona=" + persona + "]";
	}
	


}
