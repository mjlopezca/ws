package com.escolar.asignatura.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.asignatura.repository.AsignaturaRepository;
import com.escolar.asignatura.service.AsignaturaService;
import com.escolar.exception.EscolarException;
import com.escolar.persona.dao.AsignaturaDao;

@Service
public class AsignaturaServiceImpl extends  AsignaturaBaseService  implements AsignaturaService {

	@Autowired
	AsignaturaRepository asignaturaRepo;
	
	@Override
	public AsignaturaDao saveAsignatura(AsignaturaDao asignatura) {
		log.info(asignatura);
		asignatura=asignaturaRepo.save(asignatura);
		log.info(asignatura);
		return null;
	}

	@Override
	public List<AsignaturaDao> getListAsignatura() {
		List<AsignaturaDao> listAsignatura=asignaturaRepo.findAll();
		return listAsignatura;
	}

	@Override
	public AsignaturaDao getASignatura(Long idAsignatura) {
		log.info("Buscando: "+idAsignatura);
		AsignaturaDao asignatura=asignaturaRepo.findById(idAsignatura).get();
		return asignatura;
	}

	@Override
	public void updateAsignatura(Long idAsignatura, AsignaturaDao asignaturaUpdate) {
		AsignaturaDao asignaturaActual=getASignatura(idAsignatura);
		log.info("actual: "+asignaturaActual);
		if(asignaturaActual.getIdAsignatura()==asignaturaUpdate.getIdAsignatura()) {
			asignaturaActual.setIdEstado(asignaturaUpdate.getIdEstado());
			asignaturaActual.setIdGrado(asignaturaUpdate.getIdGrado());
			asignaturaActual.setNombre(asignaturaUpdate.getNombre());
			log.info("update: "+asignaturaActual);
			saveAsignatura(asignaturaActual);
		}
		else {
			throw new EscolarException(idAsignatura,"El id no coresponde a la asignatura"+asignaturaUpdate);
		}
	}

	@Override
	public void deleteAsignatura(Long idAsignatura) {
		log.info("delete idAsignatura:"+idAsignatura);
		asignaturaRepo.deleteById(idAsignatura);
	}

}
