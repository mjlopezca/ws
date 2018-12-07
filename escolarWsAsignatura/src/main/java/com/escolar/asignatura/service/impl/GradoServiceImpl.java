package com.escolar.asignatura.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.asignatura.repository.GradoRepository;
import com.escolar.persona.dao.GradoDao;

@Service
public class GradoServiceImpl extends  AsignaturaBaseService{
	@Autowired
	GradoRepository gradoRepository;
	
	
	public void saveGrado(GradoDao grado) {
		log.info(grado);
		grado=gradoRepository.save(grado);
		log.info(grado);
	}
	public List<GradoDao> getListGrado() {
		List<GradoDao> listGrado=gradoRepository.findAll();
		return listGrado;
	}
	public GradoDao getGrado(Long idGrado) {
		log.info("Busqueda:"+idGrado);
		GradoDao grado=gradoRepository.findById(idGrado).get();
		log.info("Busqueda:"+grado);
		return grado;
	}
	public void updateGrado(Long idGrado, GradoDao gradoUpdate) {
		GradoDao gradoActual=getGrado(idGrado);
		log.info("gradoActual:"+gradoActual);
		log.info("update:"+gradoUpdate);
		gradoActual.setGradoLetra(gradoUpdate.getGradoLetra());
		gradoActual.setGradoNumero(gradoUpdate.getGradoNumero());
		gradoActual.setIdEstado(gradoUpdate.getIdEstado());
		saveGrado(gradoActual);
		
	}
	public void deleteGrado(Long idGrado) {
		boolean existeGrado=gradoRepository.existsById(idGrado);
		if(existeGrado) {
			gradoRepository.deleteById(idGrado);
		}
	}
}
