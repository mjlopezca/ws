package com.escolar.persona.api;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.persona.dao.PersonaMoralDao;
import com.escolar.persona.dto.PersonaMoralDto;
import com.escolar.persona.service.impl.PersonaMoralService;

@RestController
@RequestMapping("escolar/persona")
public class PersonaMoralApi extends PersonaApiHelper{
	
	PersonaMoralService personaMoralService;
	
	private final static Logger log = LogManager.getLogger(PersonaMoralApi.class);
	
	@RequestMapping(value = "/moral",  method=RequestMethod.POST)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	public void  newPersonaMoral(@RequestBody PersonaMoralDto personaMoralRequest) {
		log.info("POST "+personaMoralRequest);
		PersonaMoralDao personaMoral=mapper.map(personaMoralRequest, PersonaMoralDao.class);		
		personaMoralService.personaMoralSave(personaMoral);		
	}
	
	@RequestMapping(value="/moral",method=RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('read')")
	public List<PersonaMoralDto> getListPersonaMoral() {
		List<PersonaMoralDao> listPersonaMoral=personaMoralService.getAllPersonaMoral();	
		List<PersonaMoralDto> listPersonaRequestDto=new ArrayList<PersonaMoralDto>();
		for(PersonaMoralDao personaMoralDto:listPersonaMoral) {
			listPersonaRequestDto.add(mapper.map(personaMoralDto,PersonaMoralDto.class));			
		}
		log.info(listPersonaRequestDto);
		return listPersonaRequestDto;
	}
	
	@RequestMapping(value="/moral/{idPersona}",method=RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	public PersonaMoralDto getPersonaMoral(@PathVariable Long idPersona) {
		log.info("GET idPersona "+idPersona);
		PersonaMoralDao personaMoralDto=personaMoralService.getPersonaMoral(idPersona);
		PersonaMoralDto personaMoralRequestDto=mapper.map(personaMoralDto, PersonaMoralDto.class);
		log.info(personaMoralRequestDto);
		return personaMoralRequestDto;
	}
	
	@RequestMapping(value="/moral/{idPersona}",method=RequestMethod.PUT)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	public void updatePersonaMoral(@PathVariable Long idPersona,@RequestBody PersonaMoralDto personaMoralRequestDto ) {
		log.info("PUT idPersona:"+idPersona);
		log.info(personaMoralRequestDto);
		PersonaMoralDao personaMoralDto=mapper.map(personaMoralRequestDto, PersonaMoralDao.class);
		personaMoralService.updatePersonaMoral(personaMoralDto, idPersona);
	}
	
	@RequestMapping(value="/moral/{idPersona}",method = RequestMethod.DELETE)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)
	public void deletePersonaMoral(@PathVariable Long idPersona) {
		log.info("DELETE idPersona:"+idPersona);
		personaMoralService.deletePersonaMoral(idPersona);
	}
	
	
	

}
