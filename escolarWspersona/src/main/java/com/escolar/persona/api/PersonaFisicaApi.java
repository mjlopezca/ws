package com.escolar.persona.api;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.persona.dto.PersonaFisicaDto;
import com.escolar.persona.repository.PersonaFisicaRepository;
import com.escolar.persona.service.impl.BaseService;
import com.escolar.persona.service.impl.PersonaFisicaService;


@RestController
@RequestMapping("escolar/persona")
public class PersonaFisicaApi extends BaseService{
	@Autowired
	PersonaFisicaService personaFisicaService;
	
	@RequestMapping(value = "/fisica",  method=RequestMethod.POST)
	public PersonaFisicaDto CreaPersonaFisica(@RequestBody PersonaFisicaDto personaFisicaRequest) {
		log.info("POST"+personaFisicaRequest);
		PersonaFisicaRepository personaFisica=mapper.map(personaFisicaRequest, PersonaFisicaRepository.class);
		PersonaFisicaRepository personaSave= personaFisicaService.personaFisicaSave(personaFisica);
		PersonaFisicaDto personaFisicaResponse=mapper.map(personaSave, PersonaFisicaDto.class);
		return personaFisicaResponse;
	}
	
	@RequestMapping(value = "/fisica", method = RequestMethod.GET)
	@ResponseBody 
	public List<PersonaFisicaDto> getAllPersonaFisica(@RequestParam(required=false, value="q") String filter, @RequestParam(required=false, value="fields") String fields) {
		log.info("GET filter"+filter);
		log.info("fields"+fields);
		
		List<PersonaFisicaRepository> personaFisicaAll = personaFisicaService.getAllPersonaFisica();
		List<PersonaFisicaDto> personaFisicaResponse=new ArrayList<PersonaFisicaDto>();
		for(PersonaFisicaRepository personaFisica:personaFisicaAll) {
			personaFisicaResponse.add(mapper.map(personaFisica, PersonaFisicaDto.class));
			
		}
		log.info(personaFisicaResponse);
		return personaFisicaResponse;
	
	}
	
	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)	
	public void borrarFisica(@PathVariable Long idPersona) {
		log.info("GET idPersona"+idPersona);
		personaFisicaService.deletePersonaFisica(idPersona);
		
	}
	
	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.GET)
	@ResponseBody 
	public PersonaFisicaDto getPersonaFisica(@PathVariable Long idPersona) {
		log.info("GET idPersona"+idPersona);
		PersonaFisicaRepository personaFisica= personaFisicaService.getPersonaFisica(idPersona);
		log.info(personaFisica);
		PersonaFisicaDto personaFisicaRequest=mapper.map(personaFisica, PersonaFisicaDto.class);
		return personaFisicaRequest;
		
	}

	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)	
	public void updatePersonaFiscia(@PathVariable Long idPersona, @RequestBody PersonaFisicaDto personaFisicaRequest) {
		log.info("idPersona"+idPersona);		
		PersonaFisicaRepository personaFisica=mapper.map(personaFisicaRequest, PersonaFisicaRepository.class);
		log.info(personaFisica);
		personaFisicaService.updatePersona(personaFisica,idPersona);
		
	}
}