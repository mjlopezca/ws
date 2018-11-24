package com.escolar.persona.api;


import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
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
import com.escolar.persona.dto.PersonaFisicaRequestDto;
import com.escolar.persona.service.impl.PersonaFisicaService;


@RestController
@RequestMapping("escolar/persona")
public class PersonaFisicaApi {
	@Autowired
	PersonaFisicaService personaFisicaService;
	
	@Autowired
	DozerBeanMapper mapper;
	
    private final static Logger log = LogManager.getLogger(PersonaFisicaApi.class);
	

	@RequestMapping(value = "/fisica",  method=RequestMethod.POST)
	public PersonaFisicaRequestDto CreaPersonaFisica(@RequestBody PersonaFisicaRequestDto personaFisicaRequest) {
		log.info("POST"+personaFisicaRequest);
		PersonaFisicaDto personaFisica=mapper.map(personaFisicaRequest, PersonaFisicaDto.class);
		PersonaFisicaDto personaSave= personaFisicaService.personaFisicaSave(personaFisica);
		PersonaFisicaRequestDto personaFisicaResponse=mapper.map(personaSave, PersonaFisicaRequestDto.class);
		return personaFisicaResponse;
	}
	
	@RequestMapping(value = "/fisica", method = RequestMethod.GET)
	@ResponseBody 
	public List<PersonaFisicaRequestDto> getAllPersonaFisica(@RequestParam(required=false, value="q") String filter, @RequestParam(required=false, value="fields") String fields) {
		log.info("GET filter"+filter);
		log.info("fields"+fields);
		
		List<PersonaFisicaDto> personaFisicaAll = personaFisicaService.getAllPersonaFisica();
		List<PersonaFisicaRequestDto> personaFisicaResponse=new ArrayList<PersonaFisicaRequestDto>();
		for(PersonaFisicaDto personaFisica:personaFisicaAll) {
			personaFisicaResponse.add(mapper.map(personaFisica, PersonaFisicaRequestDto.class));
			
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
	public PersonaFisicaRequestDto getPersonaFisica(@PathVariable Long idPersona) {
		log.info("GET idPersona"+idPersona);
		PersonaFisicaDto personaFisica= personaFisicaService.getPersonaFisica(idPersona);
		log.info(personaFisica);
		PersonaFisicaRequestDto personaFisicaRequest=mapper.map(personaFisica, PersonaFisicaRequestDto.class);
		return personaFisicaRequest;
		
	}

	@RequestMapping(value = "/fisica/{idPersona}", method = RequestMethod.PUT)
	@ResponseStatus(value=HttpStatus.OK)	
	public void updatePersonaFiscia(@PathVariable Long idPersona, @RequestBody PersonaFisicaRequestDto personaFisicaRequest) {
		log.info("idPersona"+idPersona);		
		PersonaFisicaDto personaFisica=mapper.map(personaFisicaRequest, PersonaFisicaDto.class);
		log.info(personaFisica);
		personaFisicaService.updatePersona(personaFisica,idPersona);
		
	}
}