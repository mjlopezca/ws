package com.escolar.persona.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.escolar.enums.Sexo;

@Entity
@Table(name="FISICA")
public class PersonaFisicaDto implements Serializable {
	
	
    private static final long serialVersionUID = 1l;
	
	@Id
	Long idPersona;
		
	String nombre;	
	String apellidoPaterno;	
	String apellidoMaterno;	
	Date fechaNacimiento;
	@Enumerated(EnumType.ORDINAL)
	Sexo sexo;	
	@OneToOne   
	@JoinColumn(name = "id_persona")
	private PersonaDto persona;
	
	
	public PersonaDto getPersona() {
		return persona;
	}
	public void setPersona(PersonaDto persona) {
		this.persona = persona;
	}
	public PersonaFisicaDto() {
		
	}
	public PersonaFisicaDto(Long idPersona, String nombre, String apellidoPaterno, String apellidoMaterno,
			Date fechaNacimiento, Sexo sexo) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = sexo;
	}
	

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
	@Override
	public String toString() {
		return "PersonaFisica [idPersona=" + idPersona + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno
				+ ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", sexo=" + sexo
				+ ", persona=" + persona + "]";
	}

}
