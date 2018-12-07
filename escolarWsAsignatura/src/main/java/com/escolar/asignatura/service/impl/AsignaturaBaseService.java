package com.escolar.asignatura.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AsignaturaBaseService {
	protected final Logger log = LogManager.getLogger(getClass());
	
	@Autowired
	protected DozerBeanMapper mapper;	
	
}
