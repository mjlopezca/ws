package es.carlosgarcia.smallnotes.repository;

import es.carlosgarcia.smallnotes.model.User;

/**
 * Repository for User
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
public interface UserRepository extends GenericRepository<User>, java.io.Serializable {
	public User getUserByEmail(String email);
}
