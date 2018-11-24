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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.persona.dto.PersonaMoralDto;
import com.escolar.persona.dto.PersonaMoralRequestDto;
import com.escolar.persona.service.impl.PersonaMoralService;

@RestController
@RequestMapping("escolar/persona")
public class PersonaMoralApi {
	@Autowired
	DozerBeanMapper mapper;
	@Autowired
	PersonaMoralService personaMoralService;
	
	private final static Logger log = LogManager.getLogger(PersonaMoralApi.class);
	
	@RequestMapping(value = "/moral",  method=RequestMethod.POST)
	public void  newPersonaMoral(@RequestBody PersonaMoralRequestDto personaMoralRequest) {
		log.info("POST "+personaMoralRequest);
		PersonaMoralDto personaMoral=mapper.map(personaMoralRequest, PersonaMoralDto.class);		
		personaMoralService.personaMoralSave(personaMoral);		
	}
	@RequestMapping(value="/moral",method=RequestMethod.GET)
	public List<PersonaMoralRequestDto> getListPersonaMoral() {
		List<PersonaMoralDto> listPersonaMoral=personaMoralService.getAllPersonaMoral();	
		List<PersonaMoralRequestDto> listPersonaRequestDto=new ArrayList<PersonaMoralRequestDto>();
		for(PersonaMoralDto personaMoralDto:listPersonaMoral) {
			listPersonaRequestDto.add(mapper.map(personaMoralDto,PersonaMoralRequestDto.class));			
		}
		log.info(listPersonaRequestDto);
		return listPersonaRequestDto;
	}
	@RequestMapping(value="/moral/{idPersona}",method=RequestMethod.GET)
	public PersonaMoralRequestDto getPersonaMoral(@PathVariable Long idPersona) {
		log.info("GET idPersona "+idPersona);
		PersonaMoralDto personaMoralDto=personaMoralService.getPersonaMoral(idPersona);
		PersonaMoralRequestDto personaMoralRequestDto=mapper.map(personaMoralDto, PersonaMoralRequestDto.class);
		log.info(personaMoralRequestDto);
		return personaMoralRequestDto;
	}
	
	@RequestMapping(value="/moral/{idPersona}",method=RequestMethod.PUT)
	public void updatePersonaMoral(@PathVariable Long idPersona,@RequestBody PersonaMoralRequestDto personaMoralRequestDto ) {
		log.info("PUT idPersona:"+idPersona);
		log.info(personaMoralRequestDto);
		PersonaMoralDto personaMoralDto=mapper.map(personaMoralRequestDto, PersonaMoralDto.class);
		personaMoralService.updatePersonaMoral(personaMoralDto, idPersona);
	}
	
	@RequestMapping(value="/moral/{idPersona}",method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void deletePersonaMoral(@PathVariable Long idPersona) {
		log.info("DELETE idPersona:"+idPersona);
		personaMoralService.deletePersonaMoral(idPersona);
	}
	
	
	

}
