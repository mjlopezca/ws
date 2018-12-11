package com.escolar.asignatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.GrupoDao;

public interface GrupoRepository extends JpaRepository<GrupoDao, Long> {

}
