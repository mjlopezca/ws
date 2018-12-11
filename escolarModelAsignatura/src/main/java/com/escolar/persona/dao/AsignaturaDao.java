package com.escolar.persona.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.escolar.enums.Estado;

@Entity
@Table(name="ASIGNATURA")
public class AsignaturaDao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idAsignatura;
	String nombre;
	Estado idEstado;	
	@OneToOne   
	@JoinColumn(name = "id_grado")
	GradoDao grado;
	
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
	public GradoDao getGrado() {
		return grado;
	}
	public void setGrado(GradoDao grado) {
		this.grado = grado;
	}
	@Override
	public String toString() {
		return "AsignaturaDao [idAsignatura=" + idAsignatura + ", nombre=" + nombre + ", idEstado=" + idEstado
				+ ", grado=" + grado + "]";
	}
	
	
}
