package com.escolar.persona.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.escolar.persona.dao.TelefonoDao;
import com.escolar.persona.repository.TelefonoRepository;

@Service
public class TelefonoService extends BaseService{
	@Autowired
	TelefonoRepository telefonoRepositorio;
	
	public TelefonoDao saveTelefono(TelefonoDao telefono) {
		boolean isTruePersona=personaService.buscarSiExiste(telefono.getIdPersona());
		Assert.isTrue(isTruePersona,"no se encontro persona con el id:"+telefono.getIdPersona());
		telefono=telefonoRepositorio.save(telefono);
		return telefono ;
	}
	
	public List<TelefonoDao> getAllTelefono() {
		 List<TelefonoDao> listTelefono=telefonoRepositorio.findAll();
		return listTelefono;
	}
	
	public TelefonoDao getTelefono(Long idTelefono) {
		TelefonoDao telefono=telefonoRepositorio.findById(idTelefono).get();
		return telefono;
	}
	
	public List<TelefonoDao> getAllTelefonoPersona(Long idPersona) {
		List<TelefonoDao> listTelefono=telefonoRepositorio.findByIdPersona(idPersona);
		return listTelefono;
	}
	
	public void updateTelefono(TelefonoDao telefonoUpdate,Long idTelefono,Long idPersona) {		
		TelefonoDao telefonoActual=telefonoRepositorio.findByIdTelefonoAndIdPersona(idTelefono, idPersona);
		Assert.notNull(telefonoActual,"no se encontro el telefono para la persona con id:"+idPersona+" idTelefono:"+idTelefono);
		log.info(telefonoActual);		
		telefonoActual.setNumero(telefonoUpdate.getNumero());
		telefonoActual.setEstado(telefonoUpdate.getEstado());
		telefonoActual.setTipo(telefonoUpdate.getTipo());	
		saveTelefono(telefonoActual);
	}
	public void deleteTelefono(Long idTelefono,Long idPersona) {		
		TelefonoDao telefonoDelete=telefonoRepositorio.findByIdTelefonoAndIdPersona(idTelefono, idPersona);
		Assert.notNull(telefonoDelete,"no se encontro el telefono para la persona con id:"+idPersona+" idTelefono:"+idTelefono);
		log.info(telefonoDelete);
		telefonoRepositorio.delete(telefonoDelete);
	}
	
}
