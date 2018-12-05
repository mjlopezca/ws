package com.escolar.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.PersonaFisicaDao;

public interface PersonaFisicaRepository extends JpaRepository<PersonaFisicaDao,Long>  {

}
