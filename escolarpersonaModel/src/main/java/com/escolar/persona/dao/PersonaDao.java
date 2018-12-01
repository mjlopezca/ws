package com.escolar.persona.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.escolar.enums.Estado;
import com.escolar.enums.TipoPersona;

@Entity
@Table(name="PERSONA")
public class PersonaDao  implements Serializable {
		
    private static final long serialVersionUID = 1l;
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	Long idPersona;
	
	String rfc;
	TipoPersona tipo;
	Estado estado;
	
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public TipoPersona getTipo() {
		return tipo;
	}
	public void setTipo(TipoPersona tipo) {
		this.tipo = tipo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", rfc=" + rfc + ", tipo=" + tipo + ", estado=" + estado + "]";
	}
}
