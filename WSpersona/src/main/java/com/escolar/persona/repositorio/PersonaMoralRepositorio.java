package com.escolar.persona.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.PersonaMoralDto;


public interface PersonaMoralRepositorio extends JpaRepository<PersonaMoralDto, Long>{

}
