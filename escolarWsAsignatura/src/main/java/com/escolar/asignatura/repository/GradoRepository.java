package com.escolar.asignatura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.GradoDao;

public interface GradoRepository  extends JpaRepository<GradoDao, Long>{

}
