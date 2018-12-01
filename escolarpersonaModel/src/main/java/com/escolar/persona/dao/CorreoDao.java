package com.escolar.persona.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.escolar.enums.Estado;
import com.escolar.enums.Principal;

@Entity
@Table(name="CORREO")
public class CorreoDao implements Serializable{

	 private static final long serialVersionUID = 1l;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	Long idCorreo;
	
	String correo;
	@Enumerated(EnumType.ORDINAL)
	Estado estado;
	Long idPersona;
	@Enumerated(EnumType.ORDINAL)
	Principal principal;
	
	public Long getIdCorreo() {
		return idCorreo;
	}
	public void setIdCorreo(Long idCorreo) {
		this.idCorreo = idCorreo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Principal getPrincipal() {
		return principal;
	}
	public void setPrincipal(Principal principal) {
		this.principal = principal;
	}
	
	@Override
	public String toString() {
		return "CorreoDto [idCorreo=" + idCorreo + ", correo=" + correo + ", estado=" + estado + ", idPersona="
				+ idPersona + ", principal=" + principal + "]";
	}
	
	
	

}
