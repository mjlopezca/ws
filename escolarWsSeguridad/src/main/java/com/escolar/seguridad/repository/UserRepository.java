package com.escolar.seguridad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.escolar.seguridad.dao.UserDao;

@Repository
public interface UserRepository extends JpaRepository<UserDao, String> {
	UserDao findByclaveUsuario(String username);
}
