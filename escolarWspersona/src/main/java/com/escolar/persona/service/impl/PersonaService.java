package com.escolar.persona.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.persona.dao.PersonaDao;
import com.escolar.persona.repository.PersonaRepository;

@Service
public class PersonaService extends BaseService{
	@Autowired
	PersonaRepository personaRepositorio;
	
	
	public PersonaDao savePersona(PersonaDao persona) {		
		return personaRepositorio.save(persona);
	}
	public PersonaDao getPersona(Long idPersona) {		
		return personaRepositorio.findById(idPersona).get();
	}
	public void updatePersona(PersonaDao personaUpdate, Long idPersona) {
		PersonaDao personaActual=getPersona(idPersona);
		personaActual.setEstado(personaUpdate.getEstado());
		personaActual.setRfc(personaUpdate.getRfc());	
		savePersona(personaActual);
	}
	public List<PersonaDao> getAllPersona() {
		List<PersonaDao> listPersona=personaRepositorio.findAll();
		return listPersona;
	}
	public boolean buscarSiExiste(Long idPersona) {
		return personaRepositorio.existsById(idPersona);
	}
}
