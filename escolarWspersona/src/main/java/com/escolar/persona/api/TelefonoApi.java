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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.persona.dao.TelefonoDao;
import com.escolar.persona.dto.TelefonoDto;
import com.escolar.persona.service.impl.TelefonoService;;

@RestController
@RequestMapping("escolar")
public class TelefonoApi extends PersonaApiHelper{
	@Autowired
	TelefonoService telefonoService;		
	
	@RequestMapping(value="/telefono", method=RequestMethod.POST)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	public void setTelefono(@RequestBody TelefonoDto telefonoRequest) {
		log.info(telefonoRequest);			
		TelefonoDao telefonoDto=mapper.map(telefonoRequest, TelefonoDao.class);
		telefonoDto=telefonoService.saveTelefono(telefonoDto);
		log.info(telefonoDto);
	}
	
//	@RequestMapping(value="/telefono", method=RequestMethod.GET)
//	public List<TelefonoRequest> getAllTelefono() {
//		List<TelefonoDto> listTelefono=telefonoService.getAllTelefono();
//		List<TelefonoRequest> listTelefonoRequest=new ArrayList<TelefonoRequest>();
//		for(TelefonoDto telefono:listTelefono) {
//			listTelefonoRequest.add(mapper.map(telefono, TelefonoRequest.class));
//		}
//		
//		return listTelefonoRequest;
//	}
	
	@RequestMapping(value="/telefono/{idPersona}",method=RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('read')")
	public List<TelefonoDto> getTelefono(@PathVariable Long idPersona) {
		List<TelefonoDao> listTelefono=telefonoService.getAllTelefonoPersona(idPersona);
		List<TelefonoDto> listTelefonoRequest=new ArrayList<TelefonoDto>();
		for(TelefonoDao telefono:listTelefono) {
			listTelefonoRequest.add(mapper.map(telefono, TelefonoDto.class));
		}		
		return listTelefonoRequest;
	}
	@RequestMapping(value="/telefono/{idPersona}/{idTelefono}",method=RequestMethod.PUT)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	public void updateTelefono(@PathVariable Long idPersona,@PathVariable Long idTelefono,@RequestBody TelefonoDto telefonoRequest ) {
		log.info("PUT idPersona:"+idPersona+" idTelefono:"+idTelefono);
		log.info(telefonoRequest);
		TelefonoDao teefonoUpdate=mapper.map(telefonoRequest, TelefonoDao.class);
		telefonoService.updateTelefono(teefonoUpdate, idTelefono,idPersona);	
	}
	
	@RequestMapping(value="/telefono/{idPersona}/{idTelefono}",method = RequestMethod.DELETE)
	@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_PERSONA') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)
	public void deletePersonaMoral(@PathVariable Long idPersona,@PathVariable Long idTelefono) {
		log.info("DELETE telefono idPersona:"+idPersona+" idTelefono:"+idTelefono);
		telefonoService.deleteTelefono(idTelefono,idPersona);
		
	}
}
