package com.escolar.persona.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.escolar.persona.dao.DireccionDao;
import com.escolar.persona.repository.DireccionRepository;

@Service
public class DireccionService extends BaseService{
	@Autowired
	DireccionRepository direccionRepositorio;
			
	public void saveDireccion(DireccionDao direccion) {
		log.info(direccion);
		boolean isTruePersona=personaService.buscarSiExiste(direccion.getIdPersona());
		Assert.isTrue(isTruePersona,"no se encontro persona con el id:"+direccion.getIdPersona());
		direccion=direccionRepositorio.save(direccion);
		log.info(direccion);
	}
	
	public List<DireccionDao> getAllDireccion(){
		List<DireccionDao> listDireccion=direccionRepositorio.findAll();
		return listDireccion;
	}
	
	public DireccionDao getDireccion(Long idDireccion,Long idPersona) {
		DireccionDao direccion=direccionRepositorio.findByIdDireccionAndIdPersona(idDireccion, idPersona);
		return direccion;
	}
	public List<DireccionDao> getAllDireccionPersona(Long idPersona){
		log.info(idPersona);
		List<DireccionDao> listDireccion=direccionRepositorio.findByIdPersona(idPersona);
		return listDireccion;
	}
	
	public void updateDireccion(DireccionDao direccionUpdate, Long idDireccion,Long idPersona) {
		DireccionDao direccionActual=getDireccion(idDireccion,idPersona);
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
		DireccionDao direccionDelete=getDireccion(idDireccion,idPersona);
		Assert.notNull(direccionDelete,"no se encontro el correo para la persona con id:"+idPersona+" idDireccion:"+idDireccion);
		log.info("Borrar "+direccionDelete);	
		direccionRepositorio.delete(direccionDelete);
	}
}
