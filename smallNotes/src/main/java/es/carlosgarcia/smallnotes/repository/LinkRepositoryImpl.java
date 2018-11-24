package es.carlosgarcia.smallnotes.repository;

import org.springframework.stereotype.Repository;

import es.carlosgarcia.smallnotes.model.Link;

/**
 * Repository for Links of Notes Impl
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@Repository
public class LinkRepositoryImpl extends GenericRepositoryImpl<Link> implements LinkRepository {
	private static final long serialVersionUID = -4599832253548800012L;
}
