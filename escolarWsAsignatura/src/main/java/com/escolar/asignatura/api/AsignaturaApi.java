package com.escolar.asignatura.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.escolar.asignatura.service.AsignaturaService;
import com.escolar.persona.dao.AsignaturaDao;
import com.escolar.persona.dto.AsignaturaDto;

@RestController
@RequestMapping("escolar")
public class AsignaturaApi extends AsignaturaApiHelper{
	@Autowired
	AsignaturaService asignaturaService;

	@RequestMapping(value="/asignatura/{idAsignatura}",method=RequestMethod.GET)
	@ResponseBody
	public AsignaturaDto getAsignatura(@PathVariable Long idAsignatura) {
		AsignaturaDao asignaturaDao= asignaturaService.getASignatura(idAsignatura);
		AsignaturaDto asignaturaDto= mapper.map(asignaturaDao, AsignaturaDto.class);
		return asignaturaDto;
	}
	
	@RequestMapping(value="/asignatura",method=RequestMethod.GET)
	@ResponseBody
	public List<AsignaturaDto> getListASignatura(){
		List<AsignaturaDao>listAsignaturaDao=asignaturaService.getListAsignatura();
		List<AsignaturaDto>listAsignaturaDto=new ArrayList<>();
		for(AsignaturaDao asig: listAsignaturaDao) {
			listAsignaturaDto.add(mapper.map(asig, AsignaturaDto.class));
		}
		return listAsignaturaDto;
	}
	@RequestMapping(value="/asignatura",method=RequestMethod.POST)
	@ResponseBody
	public void saveAsignatura(@RequestBody AsignaturaDto asigaturaDto) {
		AsignaturaDao asigDao=mapper.map(asigaturaDto, AsignaturaDao.class);
		asignaturaService.saveAsignatura(asigDao);
	}
	
	@RequestMapping(value="/asignatura/{idAsignatura}",method=RequestMethod.PUT)
	@ResponseBody
	public void updateAsignatura(@PathVariable Long idAsignatura,@RequestBody AsignaturaDto asigaturaDto) {
		AsignaturaDao asignaturaUpdate=mapper.map(asigaturaDto, AsignaturaDao.class);
		asignaturaService.updateAsignatura(idAsignatura, asignaturaUpdate);
	}
	
	@RequestMapping(value="/asignatura/{idAsignatura}",method=RequestMethod.DELETE)
	@ResponseBody
	public void deleteAsignatura(@PathVariable Long idAsignatura) {		
		asignaturaService.deleteAsignatura(idAsignatura);
	}
}
