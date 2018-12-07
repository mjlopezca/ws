package com.escolar.persona.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.enums.Estado;
import com.escolar.persona.dao.PersonaDao;
import com.escolar.persona.dao.PersonaFisicaDao;
import com.escolar.persona.repository.PersonaFisicaRepository;


@Service
public class PersonaFisicaService extends PersonaBaseService{
	
   @Autowired
   PersonaFisicaRepository personaFisicaDao;
  
   public PersonaFisicaDao personaFisicaSave (PersonaFisicaDao personaFisica) {
	   log.info(personaFisica); 
	   PersonaDao persona= personaService.savePersona(personaFisica.getPersona());
	  Long idPersona=persona.getIdPersona();
	  personaFisica.setIdPersona(idPersona);
	  return personaFisicaDao.save(personaFisica);
   }
   
   public  List<PersonaFisicaDao> getAllPersonaFisica() throws Exception{
	   List<PersonaFisicaDao> listPersonafisica=personaFisicaDao.findAll();	   
	   return listPersonafisica;
   }
   
	public void deletePersonaFisica(Long idPersona) throws Exception{
		PersonaDao personaDelete =personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.BAJA);
		personaService.savePersona(personaDelete);
	}
	public void DesactivarPersonaFisica(Long idPersona) throws Exception{	
		PersonaDao personaDelete =personaService.getPersona(idPersona);
		personaDelete.setEstado(Estado.INACTIVO);
		personaService.savePersona(personaDelete);
	}
	
	public PersonaFisicaDao getPersonaFisica(Long idPersona){
		PersonaFisicaDao personaFisica;
		personaFisica=personaFisicaDao.findById(idPersona).get();			
		return personaFisica;		
	}
	
	public void updatePersona(PersonaFisicaDao personaFisicaUpdate, Long idPersona) {		
		try {
			PersonaFisicaDao personaFisicaActual;
			personaFisicaActual = getPersonaFisica(idPersona);
			personaFisicaActual.setNombre(personaFisicaUpdate.getNombre());		
			personaFisicaActual.setApellidoMaterno(personaFisicaUpdate.getApellidoMaterno());
			personaFisicaActual.setApellidoPaterno(personaFisicaUpdate.getApellidoPaterno());
			personaFisicaActual.setFechaNacimiento(personaFisicaUpdate.getFechaNacimiento());
			personaFisicaActual.setSexo(personaFisicaUpdate.getSexo());
			personaFisicaActual.setPersona(personaFisicaUpdate.getPersona());
			personaFisicaSave(personaFisicaActual);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}
}
