package com.escolar.persona.api;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.persona.dto.CorreoDto;
import com.escolar.persona.repository.CorreoRepository;
import com.escolar.persona.service.impl.BaseService;
import com.escolar.persona.service.impl.CorreoService;



@RestController
@RequestMapping("escolar")
public class CorreoApi extends BaseService{
	@Autowired
	CorreoService correoService;		

	
	@RequestMapping(value="/correo", method=RequestMethod.POST)
	public void setCorreo(@RequestBody CorreoDto correoRequest) {
		log.info(correoRequest);			
		CorreoRepository correoDto=mapper.map(correoRequest, CorreoRepository.class);
		correoDto=correoService.saveCorreo(correoDto);
		log.info(correoDto);
	}
	
//	@RequestMapping(value="/telefono", method=RequestMethod.GET)
//	public List<CorreoRequest> getAllTelefono() {
//		List<TelefonoDto> listTelefono=telefonoService.getAllTelefono();
//		List<CorreoRequest> listCorreoRequest=new ArrayList<CorreoRequest>();
//		for(TelefonoDto telefono:listTelefono) {
//			listCorreoRequest.add(mapper.map(telefono, CorreoRequest.class));
//		}
//		
//		return listCorreoRequest;
//	}
	
	@RequestMapping(value="/correo/{idPersona}",method=RequestMethod.GET)
	public List<CorreoDto> getTelefono(@PathVariable Long idPersona) {
		List<CorreoRepository> ListCorreo=correoService.getAllCorreoPersona(idPersona);
		List<CorreoDto> listCorreoRequest=new ArrayList<CorreoDto>();
		for(CorreoRepository correo:ListCorreo) {
			listCorreoRequest.add(mapper.map(correo, CorreoDto.class));
		}		
		return listCorreoRequest;
	}
	@RequestMapping(value="/correo/{idPersona}/{idCorreo}",method=RequestMethod.PUT)
	public void updateTelefono(@PathVariable Long idPersona,@PathVariable Long idCorreo,@RequestBody CorreoDto CorreoRequest ) {
		log.info("PUT idPersona:"+idPersona+" idTelefono:"+idCorreo);
		log.info(CorreoRequest);
		CorreoRepository correoUpdate=mapper.map(CorreoRequest, CorreoRepository.class);
		correoService.updateCorreo(correoUpdate, idCorreo, idPersona);	
	}
	
	@RequestMapping(value="/correo/{idPersona}/{idCorreo}",method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void deletePersonaMoral(@PathVariable Long idPersona,@PathVariable Long idCorreo) {
		log.info("DELETE correo idPersona:"+idPersona+" idTelefono:"+idCorreo);
		correoService.deleteCorreo(idCorreo, idPersona);
		
	}
}
