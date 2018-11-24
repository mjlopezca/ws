package es.carlosgarcia.smallnotes.web.oauth2;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Show the confirmation page for accesing to protected resources.
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@Controller
@SessionAttributes(types = AuthorizationRequest.class)
public class AccessConfirmationController {
	private static final Logger logger  = LoggerFactory.getLogger(AccessConfirmationController.class);

	  private ClientDetailsService clientDetailsService;

	  public AccessConfirmationController(){
		  super();
		  logger.debug("creating AccessConfirmationController"); 
	  }

	  @Autowired
	  public void setClientDetailsService(ClientDetailsService clientDetailsService){
		  this.clientDetailsService = clientDetailsService;
	  }
	  
	  @RequestMapping("/oauth/confirm_access")
	  public String handleRequestInternal(ModelMap model, HttpServletRequest request,  @ModelAttribute AuthorizationRequest authorizationRequest){
		  logger.debug("AccessConfirmationController.getAccessConfirmation clientAuth {}", authorizationRequest);  

		  ClientDetails client = clientDetailsService.loadClientByClientId(authorizationRequest.getClientId());
	    
		  model.put("auth_request", authorizationRequest);
		  model.put("client", client);
		  
		 return "oauth/access_confirmation";
	  }
	  
	  @RequestMapping("/oauth/error")
	  public String handleError(Map<String,Object> model) throws Exception {
		  // We can add more stuff to the model here for JSP rendering.  If the client was a machine then
		  // the JSON will already have been rendered.
		  model.put("message", "There was a problem with the OAuth2 protocol");
		  return "oauth_error";
	  }
}



