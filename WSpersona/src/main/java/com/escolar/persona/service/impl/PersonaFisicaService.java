package com.escolar.persona.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.enums.Estado;
import com.escolar.persona.dto.PersonaDto;
import com.escolar.persona.dto.PersonaFisicaDto;
import com.escolar.persona.repositorio.PersonaFisicaRepositorio;


@Service
public class PersonaFisicaService {
	
   @Autowired
   PersonaFisicaRepositorio personaFisicaDao;
   @Autowired
   PersonaService personaService;
   private final static Logger log = LogManager.getLogger(PersonaFisicaService.class);
   
   public PersonaFisicaDto personaFisicaSave (PersonaFisicaDto personaFisica) {
	   log.info(personaFisica); 
	  PersonaDto persona= personaService.savePersona(personaFisica.getPersona());
	  Long idPersona=persona.getIdPersona();
	  personaFisica.setIdPersona(idPersona);
	  return personaFisicaDao.save(personaFisica);
   }
   
   public  List<PersonaFisicaDto> getAllPersonaFisica(){
	   List<PersonaFisicaDto> listPersonafisica=personaFisicaDao.findAll();
	   return listPersonafisica;
   }
   
	public void deletePersonaFisica(Long idPersona) {
		PersonaDto personaDelete =personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.BAJA);
		personaService.savePersona(personaDelete);
	}
	public void DesactivarPersonaFisica(Long idPersona) {		
		PersonaDto personaDelete =personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.INACTIVO);
		personaService.savePersona(personaDelete);
	}
	
	public PersonaFisicaDto getPersonaFisica(Long idPersona) {
		PersonaFisicaDto personaFisica=personaFisicaDao.findById(idPersona).get();
		return personaFisica;		
	}
	
	public void updatePersona(PersonaFisicaDto personaFisicaUpdate, Long idPersona) {
		PersonaFisicaDto personaFisicaActual= getPersonaFisica(idPersona);
		personaFisicaActual.setNombre(personaFisicaUpdate.getNombre());		
		personaFisicaActual.setApellidoMaterno(personaFisicaUpdate.getApellidoMaterno());
		personaFisicaActual.setApellidoPaterno(personaFisicaUpdate.getApellidoPaterno());
		personaFisicaActual.setFechaNacimiento(personaFisicaUpdate.getFechaNacimiento());
		personaFisicaActual.setSexo(personaFisicaUpdate.getSexo());
		personaFisicaActual.setPersona(personaFisicaUpdate.getPersona());
		personaFisicaSave(personaFisicaActual);
		
	}
}
