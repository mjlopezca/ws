package com.escolar.persona.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.enums.Estado;
import com.escolar.persona.dto.CorreoDto;
import com.escolar.persona.repositorio.CorreoRepositorio;


@Service
public class CorreoService {
	@Autowired
	CorreoRepositorio correoRepositorio;
	
	
	private static final Logger log = LogManager.getLogger(CorreoService.class);

	public void saveCorreo(CorreoDto correo) {
		log.info(correo);
		correo=correoRepositorio.save(correo);
		log.info(correo);		
	}
	public List<CorreoDto> getAllCorreo() {
		List<CorreoDto> listCorreo=correoRepositorio.findAll();
		return listCorreo;
	}
	public CorreoDto getCorreo(Long idCorreo) {
		CorreoDto correo=correoRepositorio.findById(idCorreo).get();
		return correo;
	}
	public void updateCorreo(CorreoDto correUpdate, Long idCorreo) {
		CorreoDto correoactual=getCorreo(idCorreo);
		correoactual.setCorreo(correUpdate.getCorreo());
		correoactual.setEstado(correUpdate.getEstado());
		correoactual.setPrincipal(correUpdate.getPrincipal());
		saveCorreo(correoactual);
	}
	public void deleteCorreo(Long idCorreo) {
		CorreoDto correoDelete=getCorreo(idCorreo);
		correoDelete.setEstado(Estado.BAJA);
		updateCorreo(correoDelete, idCorreo);
	}
 
}
