package com.escolar.persona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.persona.dto.DireccionDto;
import com.escolar.persona.repositorio.DireccionRepositorio;

@Service
public class DireccionService {
	@Autowired
	DireccionRepositorio direccionRepositorio;
	
	public void saveDireccion(DireccionDto direccion) {
		direccionRepositorio.save(direccion);
	}
	
	public List<DireccionDto> getAllDireccion(){
		List<DireccionDto> listDireccion=direccionRepositorio.findAll();
		return listDireccion;
	}
	
	public DireccionDto getDireccion(Long idDireccion) {
		DireccionDto direccion=direccionRepositorio.findById(idDireccion).get();
		return direccion;
	}
	
	public void updateDireccion(DireccionDto direccionUpdate, Long idDireccion) {
		DireccionDto direccionActual=getDireccion(idDireccion);
		direccionActual.setCalle(direccionUpdate.getCalle());
		direccionActual.setCodigoPostal(direccionUpdate.getCodigoPostal());
		direccionActual.setIdEstado(direccionUpdate.getIdEstado());
		direccionActual.setIdMunicipio(direccionUpdate.getIdMunicipio());
		direccionActual.setIdPais(direccionUpdate.getIdPais());
		direccionActual.setNumeroExterior(direccionUpdate.getNumeroExterior());
		direccionActual.setNumeroInterior(direccionUpdate.getNumeroInterior());
		direccionActual.setEstado(direccionUpdate.getEstado());
		saveDireccion(direccionActual);
	}
	public void deleteDireccion(Long  idDireccion) {
		DireccionDto direccionDelete=getDireccion(idDireccion);
		updateDireccion(direccionDelete, idDireccion);
	}
}
