package com.escolar.asignatura.service;

import java.util.List;


import com.escolar.persona.dao.GrupoDao;

public interface GrupoService {

	public GrupoDao saveGrupo(GrupoDao grupo);
	public List<GrupoDao> getListGrupo();
	public GrupoDao getGrupo(Long idGrupo);
	public void updateGrupo(Long idGrupo, GrupoDao grupoUpdate);
	public void deleteGrupo(Long idGrupo);
}
