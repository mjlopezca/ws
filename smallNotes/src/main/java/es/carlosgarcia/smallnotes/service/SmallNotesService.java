package es.carlosgarcia.smallnotes.service;

import java.util.List;
import java.util.Map;

import es.carlosgarcia.smallnotes.model.Note;
import es.carlosgarcia.smallnotes.model.User;

/**
 * Service to work with notes.
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
public interface SmallNotesService {
	public void create(Note note);
	public void delete(Note note);
	public void update(Note note);
	public List<Note> getAll(User user, Map<String, Object> params);
	public Note getNote(Integer noteId);
	public User getUserByEmail(String userName);
	
	/**
	 * @param user Owner of notes to be deleted.
	 * @return Number of notes deleted.
	 */
	public int deleteNotes(User user);
}