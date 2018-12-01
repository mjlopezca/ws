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

import com.escolar.persona.dto.DireccionDto;
import com.escolar.persona.repository.DireccionRepository;
import com.escolar.persona.service.impl.BaseService;
import com.escolar.persona.service.impl.DireccionService;



@RestController
@RequestMapping("escolar")
public class DireccionApi  extends BaseService{
	@Autowired
	DireccionService direccionService;

	
	@RequestMapping(value="/direccion", method=RequestMethod.POST)
	public void setCorreo(@RequestBody DireccionDto direccionRequest) {	
		log.info(direccionRequest);
		DireccionRepository direcionDto=mapper.map(direccionRequest, DireccionRepository.class);
		log.info(direcionDto);
		direccionService.saveDireccion(direcionDto);		
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
	
	@RequestMapping(value="/direccion/{idPersona}",method=RequestMethod.GET)
	public List<DireccionDto> getDirecciones(@PathVariable Long idPersona) {
		List<DireccionRepository> listDireccion=direccionService.getAllDireccionPersona(idPersona);
		List<DireccionDto> listDireccionRequest=new ArrayList<DireccionDto>();
		for(DireccionRepository personaFisica:listDireccion) {
			listDireccionRequest.add(mapper.map(personaFisica, DireccionDto.class));			
		}
		log.info(listDireccionRequest);		
		return listDireccionRequest;
	}
	@RequestMapping(value="/direccion/{idPersona}/{idDireccion}",method=RequestMethod.PUT)
	public void updateTelefono(@PathVariable Long idPersona,@PathVariable Long idDireccion,@RequestBody DireccionDto direccionRequest ) {
		log.info("PUT idPersona:"+idPersona+" idDireccion:"+idDireccion);
		log.info(direccionRequest);
		DireccionRepository direccionUpdate=mapper.map(direccionRequest, DireccionRepository.class);
		direccionService.updateDireccion(direccionUpdate, idDireccion, idPersona);			
	}
	
	@RequestMapping(value="/direccion/{idPersona}/{idDireccion}",method = RequestMethod.DELETE)
	@ResponseStatus(value=HttpStatus.OK)
	public void deletePersonaMoral(@PathVariable Long idPersona,@PathVariable Long idDireccion) {
		log.info("DELETE correo idPersona:"+idPersona+" idDireccion:"+idDireccion);
		direccionService.deleteDireccion(idDireccion, idPersona);				
	}
}
