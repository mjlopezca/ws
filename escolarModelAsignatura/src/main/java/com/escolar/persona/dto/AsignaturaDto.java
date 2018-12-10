package com.escolar.persona.dto;
import com.escolar.enums.Estado;


public class AsignaturaDto {
	
	Long idAsignatura;
	String nombre;
	Estado idEstado;
	Long idGrado;
	public Long getIdAsignatura() {
		return idAsignatura;
	}
	public void setIdAsignatura(Long idAsignatura) {
		this.idAsignatura = idAsignatura;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Estado getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}
	public Long getIdGrado() {
		return idGrado;
	}
	public void setIdGrado(Long idGrado) {
		this.idGrado = idGrado;
	}
	@Override
	public String toString() {
		return "AsignaturaDto [idAsignatura=" + idAsignatura + ", nombre=" + nombre + ", idEstado=" + idEstado
				+ ", idGrado=" + idGrado + "]";
	}
	
}
