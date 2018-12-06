package com.escolar.seguridad.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="SEGURIDAD_USUARIO")
public class UserDao implements Serializable {
	
	
    private static final long serialVersionUID = 1l;

    @Id
    @Column
    private String claveUsuario;
    @Column
    @JsonIgnore
    private String password;
    @Column(nullable=true)
    private Long idPersona;
    
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "SEGURIDAD_ROL_USUARIO",
        joinColumns = @JoinColumn(name = "clave_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<SeguridadRolDao> listRoles = new HashSet<>();

  
   
	@Column
    private int idEstado;
    
    
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
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public Set<SeguridadRolDao> getListRoles() {
		return listRoles;
	}
	public void setListRoles(Set<SeguridadRolDao> listRoles) {
		this.listRoles = listRoles;
	}
	@Override
	public String toString() {
		return "UserDao [claveUsuario=" + claveUsuario + ", password=" + password + ", idPersona=" + idPersona
				+ ", listRoles=" + listRoles + ", idEstado=" + idEstado + "]";
	}
   
}
