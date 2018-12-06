package com.escolar.seguridad.dao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.escolar.enums.Estado;

@Entity
@Table(name="SEGURIDAD_ROL")
public class SeguridadRolDao implements Serializable {
	
	
    private static final long serialVersionUID = 1l;
	
	@Id
	@Column
	Long idRol;
	String tipo;
	Estado idEstado;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,
            mappedBy = "listRoles")
    private Set<UserDao> users = new HashSet<>();
	
	public Set<UserDao> getUsers() {
		return users;
	}
	public void setUsers(Set<UserDao> users) {
		this.users = users;
	}
	public Long getIdRol() {
		return idRol;
	}
	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Estado getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(Estado idEstado) {
		this.idEstado = idEstado;
	}
	
	
	@Override
	public String toString() {
		return "SeguridadRolDao [idRol=" + idRol + ", tipo=" + tipo + ", idEstado=" + idEstado + "]";
	}
	
}
