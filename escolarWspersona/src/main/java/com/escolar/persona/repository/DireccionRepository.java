package com.escolar.persona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.DireccionDto;
import com.escolar.persona.repository.DireccionRepository;

public interface DireccionRepository extends JpaRepository<DireccionDto, Long>{
	List<DireccionDto> findByIdPersona(Long idPersona);
	DireccionDto findByIdDireccionAndIdPersona(Long idDireccion,Long idPersona);
}
