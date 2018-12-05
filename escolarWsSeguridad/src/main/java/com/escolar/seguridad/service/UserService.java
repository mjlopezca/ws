package com.escolar.seguridad.service;



import java.util.List;

import com.escolar.seguridad.dao.UserDao;

public interface UserService {

	UserDao save(UserDao user);
    List<UserDao> findAll();
    void delete(String id);
}
