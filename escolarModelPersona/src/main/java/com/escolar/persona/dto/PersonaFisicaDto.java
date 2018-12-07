package com.escolar.persona.dto;


import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.escolar.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;


public class PersonaFisicaDto {
	
	

	
	Long idPersona;
	@NotNull(message="El nombre es requerido")
	@Size(min=3, max=30, message="El nombre debe tener entre {min} y {max} caracteres")
	String nombre;
	@NotNull(message="El apellido es requerido")
	String apellidoPaterno;
	@NotNull(message="El apellido es requerido")
	String apellidoMaterno;
	@JsonFormat(pattern="yyyy/MM/dd")
	@NotNull(message="El fecha nacimiento es requerido")
	Date fechaNacimiento;
	@NotNull(message="El sexo es requerido")
	Sexo sexo;
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
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		
		this.fechaNacimiento =fechaNacimiento;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public PersonaDto getPersona() {
		return persona;
	}
	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}
	
	@Override
	public String toString() {
		return "PersonaFisicaRequest [idPersona=" + idPersona + ", nombre=" + nombre + ", apellidoPaterno="
				+ apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento
				+ ", sexo=" + sexo + ", persona=" + persona + "]";
	}

}
