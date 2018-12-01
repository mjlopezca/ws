package com.escolar.persona.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.CorreoDto;
import com.escolar.persona.repository.CorreoRepository;

public interface CorreoRepository  extends JpaRepository<CorreoDto, Long>{
	List<CorreoDto> findByIdPersona(Long idPersona);
	CorreoDto findByIdCorreoAndIdPersona(Long idCorreo,Long idPersona);
}
