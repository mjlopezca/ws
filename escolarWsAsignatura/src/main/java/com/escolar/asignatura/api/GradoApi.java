package com.escolar.asignatura.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.asignatura.service.GradoService;
import com.escolar.persona.dao.GradoDao;
import com.escolar.persona.dto.GradoDto;


@RestController
@RequestMapping("escolar")
public class GradoApi  extends AsignaturaApiHelper{
	@Autowired
	GradoService gradoService;
	
	@RequestMapping(value="/grado", method=RequestMethod.POST)
	//@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_GRADO') and #oauth2.hasScope('write')")
	@ResponseBody
	public GradoDto saveGrado(@RequestBody GradoDto gradoRequest) {
		GradoDao grado=mapper.map(gradoRequest, GradoDao.class);		
		grado=gradoService.saveGrado(grado);
		gradoRequest =mapper.map(grado, GradoDto.class);
		return gradoRequest;
	}
	
	@RequestMapping(value="/grado", method=RequestMethod.GET)
	//@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_GRADO') and #oauth2.hasScope('read')")
	@ResponseBody
	public List<GradoDto> listGrados() {
		List<GradoDao> listGradoDao=gradoService.getListGrado();
		List<GradoDto> listGradoDto=new ArrayList<>();
		for(GradoDao gradoDao: listGradoDao) {
			GradoDto gradoDto=mapper.map(gradoDao, GradoDto.class);
			listGradoDto.add(gradoDto);
		}
		
		return listGradoDto;
	}
	@RequestMapping(value="/grado/{idGrado}", method=RequestMethod.GET)
	//@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_GRADO') and #oauth2.hasScope('read')")
	@ResponseBody
	public GradoDto getGrado(@PathVariable Long idGrado) {
		GradoDao gradoDao=gradoService.getGrado(idGrado);
		GradoDto gradoDto=mapper.map(gradoDao, GradoDto.class);				
		return gradoDto;
	}
	
	@RequestMapping(value="/grado/{idGrado}", method=RequestMethod.PUT)
	//@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_GRADO') and #oauth2.hasScope('write')")
	@ResponseBody
	public void updateGrado(@PathVariable Long idGrado,@RequestBody GradoDto gradoR) {
		GradoDao gradoUpdate=mapper.map(gradoR, GradoDao.class);	
		gradoService.updateGrado(idGrado, gradoUpdate);
	
	}
	
	@RequestMapping(value="/grado/{idGrado}", method=RequestMethod.DELETE)
	//@PreAuthorize("#oauth2.isUser() and hasRole('ROLE_GRADO') and #oauth2.hasScope('write')")
	@ResponseBody
	public void deleteGrado(@PathVariable Long idGrado) {
		gradoService.deleteGrado(idGrado);
	}

}
