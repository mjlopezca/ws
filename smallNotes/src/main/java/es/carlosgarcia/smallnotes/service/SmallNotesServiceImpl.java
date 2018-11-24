package es.carlosgarcia.smallnotes.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.carlosgarcia.smallnotes.model.Link;
import es.carlosgarcia.smallnotes.model.Note;
import es.carlosgarcia.smallnotes.model.User;
import es.carlosgarcia.smallnotes.repository.LinkRepository;
import es.carlosgarcia.smallnotes.repository.NoteRepository;
import es.carlosgarcia.smallnotes.repository.UserRepository;

/**
 * Main implementation of service to work with notes.
 * All methods are transactional.
 * 
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
@Service
@Transactional(readOnly=false)
public class SmallNotesServiceImpl implements SmallNotesService {
	private final Logger logger = LoggerFactory.getLogger(SmallNotesServiceImpl.class);
	
	private NoteRepository noteRepository;
	private UserRepository userRepository;
	private LinkRepository linkRepository;
	
	public SmallNotesServiceImpl(){
		super();
	}
	
	@Inject
	public SmallNotesServiceImpl(NoteRepository noteRepository, UserRepository userRepository, LinkRepository linkRepository){
		super();
		
		this.noteRepository = noteRepository;
		this.userRepository = userRepository;
		this.linkRepository = linkRepository;
	}

	@Override
	public void create(Note note) {
		logger.debug("creating note title {}, owner: {}", note.getTitle(), note.getOwner().getId());
		
		note.setCreated(new Date());
		
		// Ensure that links has reference to its note
		List<Link> links = note.getLinks();
		if (links != null){
			for (Link link : links){
				link.setNote(note);
			}
		}
		
		this.noteRepository.saveOrUpdate(note);
		
		if (CollectionUtils.isNotEmpty(links)){
			for (Link link : links) {
				logger.debug("creating note link url: {}", link.getUrl());
				link.setNote(note);
				this.linkRepository.saveOrUpdate(link);
			}
		}
	}

	@Override
	public void update(Note note) {
		Note persistedNote = this.getNote(note.getId());
		
		if (persistedNote == null){
			throw new IllegalArgumentException("Note not exists, id: " + note.getId());
		}
		
		persistedNote.setContent(note.getContent());
		persistedNote.setTitle(note.getTitle());
		
		this.noteRepository.saveOrUpdate(persistedNote);
	}
	
	@Override
	public void delete(Note note) {
		logger.debug("deleting note id {}, owner: {}", note.getId(), note.getOwner().getId());
		this.noteRepository.delete(note);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Note> getAll(User user, Map<String, Object> params) {
		logger.debug("getting notes by user id {}", user.getId());
		
		List<Note> notes = noteRepository.getAllByUserEmail(user.getEmail(), params);
		
		logger.debug("num notes readed {}", CollectionUtils.size(notes));
		return notes;
	}

	@Override
	@Transactional(readOnly=true)
	public Note getNote(Integer noteId) {
		Note note = noteRepository.findByPK(Note.class, noteId);
		return note;
	}

	@Override
	public User getUserByEmail(String email) {
		return this.userRepository.getUserByEmail(email);
	}
	
	
	@Override
	public int deleteNotes(User user) {
		List<Note> notes = this.getAll(user, null);
		int numDeleted = 0;
		
		if (notes != null) {
			for (Note note : notes) {
				this.noteRepository.delete(note);
				numDeleted++;
			}
		}
		
		return numDeleted;
	}
}
