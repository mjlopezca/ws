package es.carlosgarcia.smallnotes.web.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

/**
 * Aprove or denied oauth2 request if it is already approved or if authentication successful.
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
public class UserApprovalHandler extends TokenServicesUserApprovalHandler {
	public boolean isApproved(AuthorizationRequest authorizationRequest, Authentication userAuthentication) {
		boolean isAlreadyApproved = super.isApproved(authorizationRequest, userAuthentication);
		return (isAlreadyApproved || (userAuthentication.isAuthenticated() && authorizationRequest.isApproved()));
	}
}


