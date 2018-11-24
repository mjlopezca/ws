package com.escolar.persona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.persona.dto.PersonaDto;
import com.escolar.persona.repositorio.PersonaRepositorio;

@Service
public class PersonaService {
	@Autowired
	PersonaRepositorio personaRepositorio;
	
	
	public PersonaDto savePersona(PersonaDto persona) {
		return personaRepositorio.save(persona);
	}
	public PersonaDto getPersona(Long idPersona) {
		return personaRepositorio.findById(idPersona).get();
	}
	public void updatePersona(PersonaDto personaUpdate, Long idPersona) {
		PersonaDto personaActual=getPersona(idPersona);
		personaActual.setEstado(personaUpdate.getEstado());
		personaActual.setRfc(personaUpdate.getRfc());	
		savePersona(personaActual);
	}
	public List<PersonaDto> getAllPersona() {
		List<PersonaDto> listPersona=personaRepositorio.findAll();
		return listPersona;
	}
}
