package com.escolar.persona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.DireccionDao;
import com.escolar.persona.repository.DireccionRepository;

public interface DireccionRepository extends JpaRepository<DireccionDao, Long>{
	List<DireccionDao> findByIdPersona(Long idPersona);
	DireccionDao findByIdDireccionAndIdPersona(Long idDireccion,Long idPersona);
}
