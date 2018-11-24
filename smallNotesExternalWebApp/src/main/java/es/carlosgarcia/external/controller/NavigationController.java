package es.carlosgarcia.external.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {
	private static final Logger logger  = LoggerFactory.getLogger(NavigationController.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public final String goToHomeView() {
		logger.debug("redirecting to home view");
		return AppConstants.HOME_VIEW;
	}
}
