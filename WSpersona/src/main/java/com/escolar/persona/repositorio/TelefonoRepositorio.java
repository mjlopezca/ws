package com.escolar.persona.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dto.TelefonoDto;

public interface TelefonoRepositorio extends JpaRepository<TelefonoDto, Long>{

}
