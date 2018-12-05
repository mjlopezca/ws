package com.escolar.persona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.CorreoDao;


public interface CorreoRepository  extends JpaRepository<CorreoDao, Long>{
	List<CorreoDao> findByIdPersona(Long idPersona);
	CorreoDao findByIdCorreoAndIdPersona(Long idCorreo,Long idPersona);
}
