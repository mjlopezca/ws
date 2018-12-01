package com.escolar.persona.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.escolar.persona.dto.DireccionDto;
import com.escolar.persona.repository.DireccionRepository;

@Service
public class DireccionService extends BaseService{
	@Autowired
	DireccionRepository direccionRepositorio;
			
	public void saveDireccion(DireccionDto direccion) {
		log.info(direccion);
		boolean isTruePersona=personaService.buscarSiExiste(direccion.getIdPersona());
		Assert.isTrue(isTruePersona,"no se encontro persona con el id:"+direccion.getIdPersona());
		direccion=direccionRepositorio.save(direccion);
		log.info(direccion);
	}
	
	public List<DireccionDto> getAllDireccion(){
		List<DireccionDto> listDireccion=direccionRepositorio.findAll();
		return listDireccion;
	}
	
	public DireccionDto getDireccion(Long idDireccion,Long idPersona) {
		DireccionDto direccion=direccionRepositorio.findByIdDireccionAndIdPersona(idDireccion, idPersona);
		return direccion;
	}
	public List<DireccionDto> getAllDireccionPersona(Long idPersona){
		log.info(idPersona);
		List<DireccionDto> listDireccion=direccionRepositorio.findByIdPersona(idPersona);
		return listDireccion;
	}
	
	public void updateDireccion(DireccionDto direccionUpdate, Long idDireccion,Long idPersona) {
		DireccionDto direccionActual=getDireccion(idDireccion,idPersona);
		log.info(direccionActual);
		direccionActual.setCalle(direccionUpdate.getCalle());
		direccionActual.setCodigoPostal(direccionUpdate.getCodigoPostal());
		direccionActual.setIdEstado(direccionUpdate.getIdEstado());
		direccionActual.setIdMunicipio(direccionUpdate.getIdMunicipio());
		direccionActual.setIdPais(direccionUpdate.getIdPais());
		direccionActual.setNumeroExterior(direccionUpdate.getNumeroExterior());
		direccionActual.setNumeroInterior(direccionUpdate.getNumeroInterior());
		direccionActual.setEstado(direccionUpdate.getEstado());
		log.info(direccionActual);	
		saveDireccion(direccionActual);
	}
	public void deleteDireccion(Long  idDireccion,Long idPersona) {
		DireccionDto direccionDelete=getDireccion(idDireccion,idPersona);
		Assert.notNull(direccionDelete,"no se encontro el correo para la persona con id:"+idPersona+" idDireccion:"+idDireccion);
		log.info("Borrar "+direccionDelete);	
		direccionRepositorio.delete(direccionDelete);
	}
}
