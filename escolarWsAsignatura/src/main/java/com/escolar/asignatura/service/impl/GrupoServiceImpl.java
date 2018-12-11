package com.escolar.asignatura.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.escolar.asignatura.repository.GrupoRepository;
import com.escolar.asignatura.service.GrupoService;
import com.escolar.exception.EscolarException;
import com.escolar.persona.dao.GrupoDao;

@Service
public class GrupoServiceImpl extends AsignaturaBaseService implements GrupoService {
	@Autowired
	GrupoRepository grupoRepository;
	
	@Override
	public GrupoDao saveGrupo(GrupoDao grupo) {
		log.info(grupo);
		grupo=grupoRepository.save(grupo);
		log.info(grupo);
		return grupo;
	}

	@Override
	public List<GrupoDao> getListGrupo() {
		List<GrupoDao> listGrupo=grupoRepository.findAll();
		return listGrupo;
	}

	@Override
	public GrupoDao getGrupo(Long idGrupo) {
		GrupoDao grupo=grupoRepository.findById(idGrupo).get();		
		return grupo;
	}

	@Override
	public void updateGrupo(Long idGrupo, GrupoDao grupoUpdate) {
		log.info("idGrupo: "+idGrupo);
		log.info(grupoUpdate);
		GrupoDao grupoActual=getGrupo(idGrupo);
		if(idGrupo==grupoUpdate.getIdGrupo()) {
			grupoActual.setCodigoGrupo(grupoUpdate.getCodigoGrupo());
			grupoActual.setIdEstado(grupoUpdate.getIdEstado());
			grupoActual.setIdInstitucion(grupoUpdate.getIdInstitucion());
			grupoActual.setNombre(grupoUpdate.getNombre());	
			saveGrupo(grupoActual);
		}
		else {
			throw new EscolarException(idGrupo,"id grupo no corresponde: "+grupoUpdate);
		}
		
	}

	@Override
	public void deleteGrupo(Long idGrupo) {
		 log.info("delete grupo: "+idGrupo);
		 grupoRepository.deleteById(idGrupo);		 		
	}

}
