package com.escolar.persona.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.PersonaFisicaDto;

public interface PersonaFisicaRepositorio extends JpaRepository<PersonaFisicaDto,Long>  {

}
