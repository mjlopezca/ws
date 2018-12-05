package com.escolar.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.escolar.persona.dao.PersonaDao;

public interface PersonaRepository extends JpaRepository<PersonaDao,Long>  {

}
