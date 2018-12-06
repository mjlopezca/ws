package com.escolar.seguridad.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.escolar.seguridad.dao.UserDao;
import com.escolar.seguridad.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
   
    @PreAuthorize("hasRole('ROLE_USUARIO') and #oauth2.hasScope('read')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<UserDao> listUser(){
    	System.err.println("entre perros");
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public UserDao create(@RequestBody UserDao user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") String id){
        userService.delete(id);
        return "success";
    }

}
