package com.escolar.persona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.persona.repository.PersonaRepository;

@Service
public class PersonaService extends BaseService{
	@Autowired
	PersonaRepository personaRepositorio;
	
	
	public PersonaRepository savePersona(PersonaRepository persona) {		
		return personaRepositorio.save(persona);
	}
	public PersonaRepository getPersona(Long idPersona) {		
		return personaRepositorio.findById(idPersona).get();
	}
	public void updatePersona(PersonaRepository personaUpdate, Long idPersona) {
		PersonaRepository personaActual=getPersona(idPersona);
		personaActual.setEstado(personaUpdate.getEstado());
		personaActual.setRfc(personaUpdate.getRfc());	
		savePersona(personaActual);
	}
	public List<PersonaRepository> getAllPersona() {
		List<PersonaRepository> listPersona=personaRepositorio.findAll();
		return listPersona;
	}
	public boolean buscarSiExiste(Long idPersona) {
		return personaRepositorio.existsById(idPersona);
	}
}
