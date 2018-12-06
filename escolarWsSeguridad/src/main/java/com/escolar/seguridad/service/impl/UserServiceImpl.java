package com.escolar.seguridad.service.impl;


import com.escolar.seguridad.dao.SeguridadRolDao;
import com.escolar.seguridad.dao.UserDao;
import com.escolar.seguridad.repository.UserRepository;
import com.escolar.seguridad.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {
	
	@Autowired
	private UserRepository userRepositorio;

	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		System.err.println(userId);
		UserDao user = userRepositorio.findById(userId).get();
		System.err.println("user");
		if(user == null){
			throw new UsernameNotFoundException("usuaro o contraseña invalida");
		}
		return new org.springframework.security.core.userdetails.User(user.getClaveUsuario(), user.getPassword(), getAuthority(user.getListRoles()));
	}

	private List<SimpleGrantedAuthority> getAuthority(Set<SeguridadRolDao> listRolDao) {
		List<SimpleGrantedAuthority> listAthority=new ArrayList<>();
		listRolDao.forEach(action->{
			
			System.err.println("action:"+action.getTipo());
			listAthority.add(new SimpleGrantedAuthority(action.getTipo()));
		});
		return listAthority;
	}

	public List<UserDao> findAll() {
		List<UserDao> listUser=userRepositorio.findAll();
		return listUser;
	}

	@Override
	public void delete(String id) {
		userRepositorio.deleteById(id);
	}

	@Override
    public UserDao save(UserDao user) {
        return userRepositorio.save(user);
    }
}
