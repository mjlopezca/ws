package com.escolar.persona.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonaMoralDto {
	
	Long idPersona;
	@NotNull(message="El nombre es requerido")
	String nombre;	
	@NotNull(message="El razonSocial es requerido")
	String razonSocial;	
	@JsonFormat(pattern="yyyy/MM/dd")
	@NotNull(message="El fecha constitucion es requerido")		
	Date fechaConstitucion;
		
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
		return "PersonaMoralRequestDto [idPersona=" + idPersona + ", nombre=" + nombre + ", razonSocial=" + razonSocial
				+ ", fechaConstitucion=" + fechaConstitucion + ", persona=" + persona + "]";
	}
	


}
