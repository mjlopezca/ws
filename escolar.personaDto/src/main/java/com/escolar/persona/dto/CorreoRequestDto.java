package com.escolar.persona.dto;

import com.escolar.enums.Estado;
import com.escolar.enums.Principal;

public class CorreoRequestDto {
	
	Long idCorreo;	
	String correo;
	Estado estado;
	Long idPersona;
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
		return "CorreoRequestDto [idCorreo=" + idCorreo + ", correo=" + correo + ", estado=" + estado + ", idPersona="
				+ idPersona + ", principal=" + principal + "]";
	}
}
