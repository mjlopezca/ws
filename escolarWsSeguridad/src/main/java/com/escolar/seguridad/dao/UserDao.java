package com.escolar.seguridad.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="SEGURIDAD_USUARIO")
public class UserDao {

    @Id
    @Column
    private String claveUsuario;
    @Column
    @JsonIgnore
    private String password;
    @Column(nullable=true)
    private Long idPersona;
    @Column
    private int idEstatus;
	public String getClaveUsuario() {
		return claveUsuario;
	}
	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdEstatus() {
		return idEstatus;
	}
	public void setIdEstatus(int idEstatus) {
		this.idEstatus = idEstatus;
	}
	@Override
	public String toString() {
		return "UserDao [claveUsuario=" + claveUsuario + ", password=" + password + ", idPersona=" + idPersona
				+ ", idEstatus=" + idEstatus + "]";
	}

   
}
