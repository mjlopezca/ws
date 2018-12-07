package com.escolar.persona.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.persona.repository.PersonaRepository;
import com.escolar.enums.Estado;
import com.escolar.persona.dao.PersonaDao;
import com.escolar.persona.dao.PersonaMoralDao;
import com.escolar.persona.repository.PersonaMoralRepository;

import java.util.List;

@Service
public class PersonaMoralService extends BaseService{
	@Autowired
	PersonaMoralRepository personaMoralRepositorio;
	
	public PersonaMoralDao personaMoralSave(PersonaMoralDao personaMoral) {		
		PersonaDao persona=personaService.savePersona(personaMoral.getPersona());
		personaMoral.setIdPersona(persona.getIdPersona());
		personaMoral.setPersona(persona);		
		personaMoral=personaMoralRepositorio.save(personaMoral);
		log.info(personaMoral);
		log.info("getIdPersona: "+personaMoral.getIdPersona());
		return personaMoral;
		
	}
	public List<PersonaMoralDao> getAllPersonaMoral() {
		List<PersonaMoralDao> listPersonaMoral= personaMoralRepositorio.findAll();
		return listPersonaMoral;
	}
	public PersonaMoralDao getPersonaMoral(Long idPersona) {
		PersonaMoralDao personaMoral=personaMoralRepositorio.findById(idPersona).get();
		return personaMoral;
	}
	public void updatePersonaMoral(PersonaMoralDao personaMoralUpdate, Long idPersona) {
		PersonaMoralDao personaActual=getPersonaMoral(idPersona);
		personaActual.setNombre(personaMoralUpdate.getNombre());
		personaActual.setRazonSocial(personaMoralUpdate.getRazonSocial());
		personaActual.setFechaConstitucion(personaMoralUpdate.getFechaConstitucion());
		personaActual.setPersona(personaMoralUpdate.getPersona());
		personaMoralSave(personaActual);
	}
	
	public void deletePersonaMoral(Long idPersona) {
		PersonaDao personaDelete=personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.BAJA);
		personaService.savePersona(personaDelete);
		
	}
	public void DesactivarPersonaMoral(Long idPersona) {
		PersonaDao personaDelete=personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.INACTIVO);
		personaService.savePersona(personaDelete);
		
	}
}
