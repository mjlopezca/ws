package com.escolar.persona.dto;


import javax.validation.constraints.Pattern;

import com.escolar.enums.Estado;
import com.escolar.enums.TipoPersona;


public class PersonaDto {
	
	Long idPersona;
	@Pattern(regexp="^([A-ZÑ&]{3,4}) ?(?:- ?)?(\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])) ?(?:- ?)?([A-Z\\d]{2})([A\\d])$/",message="Rfc invalido")
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
		return "PersonaRequest [idPersona=" + idPersona + ", rfc=" + rfc + ", tipo=" + tipo + ", estado=" + estado
				+ "]";
	}
	
	
}
