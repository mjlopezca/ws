package com.escolar.persona.dto;

import com.escolar.enums.Estado;

public class DireccionDto {
Long idDireccion;
	
	String calle;
	String numeroExterior;
	String numeroInterior;
	int idPais;
	int idEstado;
	int idMunicipio;
	String codigoPostal;
	Long idPersona;
	Estado estado;
	public Long getIdDireccion() {
		return idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(String numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public String getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(String numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public int getIdPais() {
		return idPais;
	}
	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "DireccionRequest [idDireccion=" + idDireccion + ", calle=" + calle + ", numeroExterior="
				+ numeroExterior + ", numeroInterior=" + numeroInterior + ", idPais=" + idPais + ", idEstado="
				+ idEstado + ", idMunicipio=" + idMunicipio + ", codigoPostal=" + codigoPostal + ", idPersona="
				+ idPersona + ", estado=" + estado + "]";
	}
}
