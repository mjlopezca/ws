package com.escolar.persona.dao;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.escolar.enums.Estado;
import com.escolar.enums.TipoTelefono;

@Entity
@Table(name="TELEFONO")
public class TelefonoDao {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) 
  Long idTelefono;  
  int numero;
  Estado estado;
  Long idPersona;
  @Enumerated(EnumType.ORDINAL)
  TipoTelefono tipo;
public Long getIdTelefono() {
	return idTelefono;
}
public void setIdTelefono(Long idTelefono) {
	this.idTelefono = idTelefono;
}
public int getNumero() {
	return numero;
}
public void setNumero(int numero) {
	this.numero = numero;
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
public TipoTelefono getTipo() {
	return tipo;
}
public void setTipo(TipoTelefono tipo) {
	this.tipo = tipo;
}
@Override
public String toString() {
	return "TelefonoDto [idTelefono=" + idTelefono + ", numero=" + numero + ", estado=" + estado + ", idPersona="
			+ idPersona + ", tipo=" + tipo + "]";
}
  
  
}
