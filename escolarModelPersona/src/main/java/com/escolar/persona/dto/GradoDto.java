package com.escolar.persona.dto;

import com.escolar.enums.Estado;

public class GradoDto {
	Long idGrado;
	byte gradoNumero;
	String gradoLetra;
	Estado idEstado;
	public Long getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(Long idGrado) {
		this.idGrado = idGrado;
	}
	public byte getGradoNumero() {
		return gradoNumero;
	}
	public void setGradoNumero(byte gradoNumero) {
		this.gradoNumero = gradoNumero;
	}
	public String getGradoLetra() {
		return gradoLetra;
	}
	public void setGradoLetra(String gradoLetra) {
		this.gradoLetra = gradoLetra;
	}
	public Estado getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}
	
	
}