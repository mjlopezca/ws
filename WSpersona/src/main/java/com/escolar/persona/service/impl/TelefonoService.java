package com.escolar.persona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.enums.Estado;
import com.escolar.persona.dto.TelefonoDto;
import com.escolar.persona.repositorio.TelefonoRepositorio;

@Service
public class TelefonoService {
	@Autowired
	TelefonoRepositorio telefonoRepositorio;
	
	public void saveTelefono(TelefonoDto telefono) {
		telefonoRepositorio.save(telefono);
	}
	
	public List<TelefonoDto> getAllTelefono() {
		 List<TelefonoDto> listTelefono=telefonoRepositorio.findAll();
		return listTelefono;
	}
	
	public TelefonoDto getTelefono(Long idTelefono) {
		TelefonoDto telefono=telefonoRepositorio.findById(idTelefono).get();
		return telefono;
	}
	public void updateTelefono(TelefonoDto telefonoUpdate,Long idTelefono) {
		TelefonoDto telefonoActual=getTelefono(idTelefono);
		telefonoActual.setNumero(telefonoUpdate.getNumero());
		telefonoActual.setEstado(telefonoUpdate.getEstado());
		telefonoActual.setTipo(telefonoUpdate.getTipo());	
		saveTelefono(telefonoActual);
	}
	public void deleteCorreo(Long idTelefono) {
		TelefonoDto telefonoDelete=getTelefono(idTelefono);
		telefonoDelete.setEstado(Estado.BAJA);
		updateTelefono(telefonoDelete, idTelefono);
	}
	
}
