package com.escolar.persona.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.enums.Estado;
import com.escolar.persona.dto.PersonaDto;
import com.escolar.persona.dto.PersonaMoralDto;
import com.escolar.persona.repositorio.PersonaMoralRepositorio;

import java.util.List;

@Service
public class PersonaMoralService {
	@Autowired
	PersonaMoralRepositorio personaMoralRepositorio;
	@Autowired
	PersonaService personaService;
	
	private final static Logger log = LogManager.getLogger(PersonaMoralService.class);
	
	public PersonaMoralDto personaMoralSave(PersonaMoralDto personaMoral) {		
		PersonaDto persona=personaService.savePersona(personaMoral.getPersona());
		personaMoral.setIdPersona(persona.getIdPersona());
		personaMoral.setPersona(persona);		
		personaMoral=personaMoralRepositorio.save(personaMoral);
		log.info(personaMoral);
		log.info("getIdPersona: "+personaMoral.getIdPersona());
		return personaMoral;
		
	}
	public List<PersonaMoralDto> getAllPersonaMoral() {
		List<PersonaMoralDto> listPersonaMoral= personaMoralRepositorio.findAll();
		return listPersonaMoral;
	}
	public PersonaMoralDto getPersonaMoral(Long idPersona) {
		PersonaMoralDto personaMoral=personaMoralRepositorio.findById(idPersona).get();
		return personaMoral;
	}
	public void updatePersonaMoral(PersonaMoralDto personaMoralUpdate, Long idPersona) {
		PersonaMoralDto personaActual=getPersonaMoral(idPersona);
		personaActual.setNombre(personaMoralUpdate.getNombre());
		personaActual.setRazonSocial(personaMoralUpdate.getRazonSocial());
		personaActual.setFechaConstitucion(personaMoralUpdate.getFechaConstitucion());
		personaActual.setPersona(personaMoralUpdate.getPersona());
		personaMoralSave(personaActual);
	}
	
	public void deletePersonaMoral(Long idPersona) {
		PersonaDto personaDelete=personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.BAJA);
		personaService.savePersona(personaDelete);
		
	}
	public void DesactivarPersonaMoral(Long idPersona) {
		PersonaDto personaDelete=personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.INACTIVO);
		personaService.savePersona(personaDelete);
		
	}
}
