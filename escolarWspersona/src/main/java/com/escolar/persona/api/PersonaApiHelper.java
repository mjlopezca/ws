package com.escolar.persona.api;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escolar.enums.ApiErrorCode;
import com.escolar.exception.EscolarException;
import com.escolar.exception.RestApiError;
import com.escolar.persona.service.impl.PersonaService;
import com.escolar.seguridad.dao.UserDao;
import com.escolar.seguridad.service.UserService;




public class PersonaApiHelper{
	
	protected final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	protected DozerBeanMapper mapper;	
	
	@Autowired 
	PersonaService personaService;
	@Autowired
	private UserService userService;
	public static ResponseEntity<RestApiError> createAndSendResponse(RestApiError restApiError) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		headers.setContentType(MediaType.APPLICATION_JSON);
		ResponseEntity<RestApiError> responseEntity = new ResponseEntity<RestApiError>(restApiError, headers, restApiError.getHttpStatusCode());
		return responseEntity;
	}
	

	 
	protected UserDao getLoggedUser(){
		String claveUsuario =  null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object	   principal  = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			claveUsuario =  ((UserDetails) principal).getUsername();
		} else if (principal instanceof UsernamePasswordAuthenticationToken){
			claveUsuario = ((UsernamePasswordAuthenticationToken) principal).getName();
		} else if (principal instanceof OAuth2Authentication){
			claveUsuario = ((OAuth2Authentication) principal).getUserAuthentication().getName();
		}
		log.info(principal);
		UserDao user  = userService.getUser(claveUsuario);

		return user;
	}
	
	@ExceptionHandler(EscolarException.class)
	protected @ResponseBody ResponseEntity<RestApiError> handleNoteNotFoundException(EscolarException NotFoundException, HttpServletRequest request, HttpServletResponse response) {
		log.error("error",NotFoundException);
		RestApiError restApiError = new RestApiError(HttpStatus.NOT_FOUND, ApiErrorCode.PERSONA_NOT_FOUND, NotFoundException.getMessage(), NotFoundException.getExcepcion(), this.getInfoUrl(ApiErrorCode.PERSONA_NOT_FOUND));
		return createAndSendResponse(restApiError);
	}
	
	

	@ExceptionHandler(SecurityException.class)
	protected @ResponseBody ResponseEntity<RestApiError> handleSecurityException(SecurityException exception, HttpServletRequest request, HttpServletResponse response) {
		log.error("error",exception);
		RestApiError restApiError = new RestApiError(HttpStatus.UNAUTHORIZED, ApiErrorCode.SECURITY, exception.getMessage(), exception.getMessage(), this.getInfoUrl(ApiErrorCode.SECURITY));
		return createAndSendResponse(restApiError);
	}
	
	@ExceptionHandler(Exception.class)
	protected  @ResponseBody  ResponseEntity<RestApiError> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
		log.error("error",exception);
		RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, ApiErrorCode.GENERIC, exception.getMessage(), exception.getMessage(), this.getInfoUrl(ApiErrorCode.GENERIC));
		return createAndSendResponse(restApiError);
	}
	private String getInfoUrl(ApiErrorCode code){
		return "http://sotorpe.com/api/support/" + code.ordinal();
	}
}
