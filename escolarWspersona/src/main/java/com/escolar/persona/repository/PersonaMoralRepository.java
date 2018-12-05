package com.escolar.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.PersonaMoralDao;


public interface PersonaMoralRepository extends JpaRepository<PersonaMoralDao, Long>{

}
