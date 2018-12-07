package com.escolar.persona.api;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.exception.EscolarException;
import com.escolar.persona.dao.PersonaFisicaDao;
import com.escolar.persona.dto.PersonaFisicaDto;
import com.escolar.persona.service.impl.PersonaFisicaService;



@RestController
@RequestMapping("escolar/persona")
public class PersonaFisicaApi extends PersonaApiHelper {
	@Autowired
	PersonaFisicaService personaFisicaService;
	
	@RequestMapping(value = "/fisica",  method=RequestMethod.POST)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	public PersonaFisicaDto CreaPersonaFisica(@RequestBody PersonaFisicaDto personaFisicaRequest) {
		log.info("POST"+personaFisicaRequest);
		PersonaFisicaDao personaFisica=mapper.map(personaFisicaRequest, PersonaFisicaDao.class);
		PersonaFisicaDao personaSave= personaFisicaService.personaFisicaSave(personaFisica);
		PersonaFisicaDto personaFisicaResponse=mapper.map(personaSave, PersonaFisicaDto.class);
		return personaFisicaResponse;
	}
	
	
	@RequestMapping(value = "/fisica", method = RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('read')")
	@ResponseBody 
	public List<PersonaFisicaDto> getAllPersonaFisica(@RequestParam(required=false, value="q") String filter, @RequestParam(required=false, value="fields") String fields) {
		log.info("GET filter"+filter);
		log.info("fields"+fields);
		getLoggedUser();
		List<PersonaFisicaDao> personaFisicaAll;
		try {
			personaFisicaAll = personaFisicaService.getAllPersonaFisica();
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();		
			throw new EscolarException(null,e.getClass().getName()+" "+e.getMessage(),"Error al obtener lista de persona");
		}
		List<PersonaFisicaDto> personaFisicaResponse=new ArrayList<PersonaFisicaDto>();
		for(PersonaFisicaDao personaFisica:personaFisicaAll) {
			personaFisicaResponse.add(mapper.map(personaFisica, PersonaFisicaDto.class));
			
		}
		log.info(personaFisicaResponse);
		return personaFisicaResponse;
	
	}
	
	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.DELETE)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)	
	public void borrarFisica(@PathVariable Long idPersona) {
		log.info("GET idPersona"+idPersona);
		try {
			personaFisicaService.deletePersonaFisica(idPersona);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();		
			throw new EscolarException(null,e.getClass().getName()+" "+e.getMessage(),"Error al eliminar usuario");
		}
		
	}
	
	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('read')")
	@ResponseBody 
	public PersonaFisicaDto getPersonaFisica(@PathVariable Long idPersona) {
		log.info("GET idPersona"+idPersona);
		PersonaFisicaDao personaFisica;
		personaFisica = personaFisicaService.getPersonaFisica(idPersona);	
		log.info(personaFisica);
		PersonaFisicaDto personaFisicaRequest=mapper.map(personaFisica, PersonaFisicaDto.class);
		return personaFisicaRequest;
		
	}

	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.PUT)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)	
	public void updatePersonaFiscia(@PathVariable Long idPersona, @RequestBody PersonaFisicaDto personaFisicaRequest) {
		log.info("idPersona"+idPersona);		
		PersonaFisicaDao personaFisica=mapper.map(personaFisicaRequest, PersonaFisicaDao.class);
		log.info(personaFisica);		
		personaFisicaService.updatePersona(personaFisica,idPersona);
		
		
	}
	
	

}