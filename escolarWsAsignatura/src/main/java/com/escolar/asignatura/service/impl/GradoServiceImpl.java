package com.escolar.asignatura.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.asignatura.repository.GradoRepository;
import com.escolar.persona.dao.GradoDao;

@Service
public class GradoServiceImpl {
	@Autowired
	GradoRepository gradoRepository;
	
	
	public void saveGrado(GradoDao grado) {
		gradoRepository.save(grado);
	}
	public List<GradoDao> getListGrado() {
		List<GradoDao> listGrado=gradoRepository.findAll();
		return listGrado;
	}
	public GradoDao getGrado(Long idGrado) {
		GradoDao grado=gradoRepository.findById(idGrado).get();
		return grado;
	}
}
