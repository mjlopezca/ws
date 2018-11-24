package com.escolar.persona.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.PersonaDto;

public interface PersonaRepositorio extends JpaRepository<PersonaDto,Long>  {

}
