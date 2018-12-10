package com.escolar.asignatura.service;

import java.util.List;

import com.escolar.persona.dao.GradoDao;

public interface GradoService {
	
	public GradoDao saveGrado(GradoDao grado);
	public List<GradoDao> getListGrado();
	public GradoDao getGrado(Long idGrado);
	public void updateGrado(Long idGrado, GradoDao gradoUpdate);
	public void deleteGrado(Long idGrado);
	
	

}
