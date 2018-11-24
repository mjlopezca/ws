package es.carlosgarcia.smallnotes.repository;

import java.util.List;
import java.util.Map;

import es.carlosgarcia.smallnotes.model.Note;

/**
 * Repository for Notes
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
public interface NoteRepository extends GenericRepository<Note>, java.io.Serializable {
	List<Note> getAllByUserEmail(String userEmail, Map<String,Object> filterParams);
}
