package com.escolar.persona.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.escolar.persona.dto.CorreoDto;
import com.escolar.persona.repository.CorreoRepository;


@Service
public class CorreoService extends BaseService{
	@Autowired
	CorreoRepository correoRepositorio;
	
	
	public CorreoDto saveCorreo(CorreoDto correo) {
		log.info(correo);
		boolean isTruePersona=personaService.buscarSiExiste(correo.getIdPersona());
		Assert.isTrue(isTruePersona,"no se encontro persona con el id:"+correo.getIdPersona());
		correo=correoRepositorio.save(correo);
		log.info(correo);
		return correo;
	}
	public List<CorreoDto> getAllCorreo() {
		List<CorreoDto> listCorreo=correoRepositorio.findAll();
		return listCorreo;
	}
	public CorreoDto getCorreo(Long idCorreo,Long idPersona) {
		CorreoDto correo=correoRepositorio.findByIdCorreoAndIdPersona(idCorreo, idPersona);
		return correo;
	}
	public List<CorreoDto> getAllCorreoPersona(Long idPersona){
		log.info(idPersona);
		List<CorreoDto> listCorreoPersona=correoRepositorio.findByIdPersona(idPersona);
		log.info(listCorreoPersona);
		return listCorreoPersona;
	}
	public void updateCorreo(CorreoDto correUpdate, Long idCorreo,Long idPersona) {
		CorreoDto correoActual=getCorreo(idCorreo,idPersona);
		Assert.notNull(correoActual,"no se encontro el correo para la persona con id:"+idPersona+" idCorreo:"+idCorreo);
		correoActual.setCorreo(correUpdate.getCorreo());
		correoActual.setEstado(correUpdate.getEstado());
		correoActual.setPrincipal(correUpdate.getPrincipal());
		saveCorreo(correoActual);
	}
	public void deleteCorreo(Long idCorreo,Long idPersona) {
		CorreoDto correoDelete=getCorreo(idCorreo,idPersona);
		Assert.notNull(correoDelete,"no se encontro el correo para la persona con id:"+idPersona+" idCorreo:"+idCorreo);
		log.info(correoDelete);	
		correoRepositorio.delete(correoDelete);
	}
 
}
