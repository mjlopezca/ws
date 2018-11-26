package com.escolar.persona.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.CorreoDto;

public interface CorreoRepositorio  extends JpaRepository<CorreoDto, Long>{

}
