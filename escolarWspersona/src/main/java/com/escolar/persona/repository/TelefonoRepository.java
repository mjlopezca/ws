package com.escolar.persona.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.escolar.persona.dao.TelefonoDao;



public interface TelefonoRepository extends JpaRepository<TelefonoDao, Long>{
	
	//@Query("select p from Product p where p.price > :precio")
    List<TelefonoDao>  findByIdPersona(Long idPersona);
    TelefonoDao findByIdTelefonoAndIdPersona(Long idTelefono,Long idPersona);
 
}
