package com.escolar.persona.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.escolar.enums.Estado;

@Entity
@Table(name="GRADO")
public class GradoDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
