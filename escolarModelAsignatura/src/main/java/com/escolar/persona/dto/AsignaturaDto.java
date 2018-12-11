package com.escolar.persona.dto;
import com.escolar.enums.Estado;


public class AsignaturaDto {
	
	Long idAsignatura;
	String nombre;
	Estado idEstado;
	GradoDto grado;
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
	public GradoDto getGrado() {
		return grado;
	}
	public void setGrado(GradoDto grado) {
		this.grado = grado;
	}
	@Override
	public String toString() {
		return "AsignaturaDto [idAsignatura=" + idAsignatura + ", nombre=" + nombre + ", idEstado=" + idEstado
				+ ", grado=" + grado + "]";
	}
	
	
}
