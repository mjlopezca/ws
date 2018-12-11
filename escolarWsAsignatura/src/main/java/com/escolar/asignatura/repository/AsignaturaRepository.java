package com.escolar.asignatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.AsignaturaDao;

public interface AsignaturaRepository extends JpaRepository<AsignaturaDao, Long>{

}
