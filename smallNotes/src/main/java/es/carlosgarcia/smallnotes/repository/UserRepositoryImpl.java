package es.carlosgarcia.smallnotes.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import es.carlosgarcia.smallnotes.model.User;

/**
 * Main implementation of user respository
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@Repository
public class UserRepositoryImpl extends GenericRepositoryImpl<User>  implements UserRepository {
	private static final long serialVersionUID = -4599832253548800012L;

	private final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	public UserRepositoryImpl(){
		logger.debug("creating UserRepositoryImpl");
	}
	
	@Override
	public User getUserByEmail(String email) {
		logger.debug("getting user by email {}", email);
		
		User user = null;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		List<User> items = this.getAllByQuery("select u from User u where email = :email", params);
		if (! CollectionUtils.isEmpty(items)){
			user = items.get(0);
		}
		
		return user;
	}
}
