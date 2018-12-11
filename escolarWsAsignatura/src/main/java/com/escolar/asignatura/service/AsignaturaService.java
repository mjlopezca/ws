package com.escolar.asignatura.service;

import java.util.List;
import com.escolar.persona.dao.AsignaturaDao;

public interface AsignaturaService {
	
	public AsignaturaDao saveAsignatura(AsignaturaDao asignatura);
	public List<AsignaturaDao> getListAsignatura();
	public AsignaturaDao getASignatura(Long idAsignatura);
	public void updateAsignatura(Long idAsignatura, AsignaturaDao asignaturaUpdate);
	public void deleteAsignatura(Long idAsignatura);

}
