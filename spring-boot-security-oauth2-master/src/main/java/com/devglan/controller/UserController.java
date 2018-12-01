package com.devglan.controller;

import com.devglan.model.User;
import com.devglan.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

   // @PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('read')")
    @PreAuthorize("#oauth2.isUser() and hasRole('ROLE_ADMINISTRADOR')  and #oauth2.hasScope('read')")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public List<User> listUser(){
    	getLoggedUser();
        return userService.findAll();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public User create(@RequestBody User user){
        return userService.save(user);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.isUser() and #oauth2.hasScope('write')")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "success";
    }

    /**
	 * @return The logged user
	 */
	private void getLoggedUser(){
		String email =  null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object	   principal  = authentication.getPrincipal();
		
		
		if (principal instanceof UserDetails) {
			email =  ((UserDetails) principal).getUsername();
		} else if (principal instanceof UsernamePasswordAuthenticationToken){
			email = ((UsernamePasswordAuthenticationToken) principal).getName();
		} else if (principal instanceof OAuth2Authentication){
		    email = ((OAuth2Authentication) principal).getUserAuthentication().getName();
		}
		System.err.println(principal);
	System.err.println(email);
		
	
	}
}
