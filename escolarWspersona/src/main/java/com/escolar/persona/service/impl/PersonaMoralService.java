package com.escolar.persona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.enums.Estado;
import com.escolar.persona.repository.PersonaRepository;
import com.escolar.persona.repository.PersonaMoralRepository;

import java.util.List;

@Service
public class PersonaMoralService extends BaseService{
	@Autowired
	PersonaMoralRepository personaMoralRepositorio;
	
	public PersonaMoralRepository personaMoralSave(PersonaMoralRepository personaMoral) {		
		PersonaRepository persona=personaService.savePersona(personaMoral.getPersona());
		personaMoral.setIdPersona(persona.getIdPersona());
		personaMoral.setPersona(persona);		
		personaMoral=personaMoralRepositorio.save(personaMoral);
		log.info(personaMoral);
		log.info("getIdPersona: "+personaMoral.getIdPersona());
		return personaMoral;
		
	}
	public List<PersonaMoralRepository> getAllPersonaMoral() {
		List<PersonaMoralRepository> listPersonaMoral= personaMoralRepositorio.findAll();
		return listPersonaMoral;
	}
	public PersonaMoralRepository getPersonaMoral(Long idPersona) {
		PersonaMoralRepository personaMoral=personaMoralRepositorio.findById(idPersona).get();
		return personaMoral;
	}
	public void updatePersonaMoral(PersonaMoralRepository personaMoralUpdate, Long idPersona) {
		PersonaMoralRepository personaActual=getPersonaMoral(idPersona);
		personaActual.setNombre(personaMoralUpdate.getNombre());
		personaActual.setRazonSocial(personaMoralUpdate.getRazonSocial());
		personaActual.setFechaConstitucion(personaMoralUpdate.getFechaConstitucion());
		personaActual.setPersona(personaMoralUpdate.getPersona());
		personaMoralSave(personaActual);
	}
	
	public void deletePersonaMoral(Long idPersona) {
		PersonaRepository personaDelete=personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.BAJA);
		personaService.savePersona(personaDelete);
		
	}
	public void DesactivarPersonaMoral(Long idPersona) {
		PersonaRepository personaDelete=personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.INACTIVO);
		personaService.savePersona(personaDelete);
		
	}
}
