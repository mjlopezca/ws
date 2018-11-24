package es.carlosgarcia.smallnotes.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.carlosgarcia.smallnotes.model.Link;
import es.carlosgarcia.smallnotes.model.Note;
import es.carlosgarcia.smallnotes.model.User;


/**
 * Testing for class es.carlosgarcia.smallnotes.repository.NoteRepositoryImpl
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:smallNotes-test.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class NoteRepositoryImplTest {

	private static final String EMAIL_TEST1 = "user1@smallnotes.es";
	private static final String TITLE_NOTE_TEST = "titleNoteTest";
	private static final String CONTENT_NOTE_TEST = "contentNoteTest";
	
	
	@Autowired
	private NoteRepository noteRepository;
	
	@Autowired
	private UserRepository userRepository;	
	
	@Test
	public void shouldReadUserByEmail(){
		List<Note> notes = this.noteRepository.getAllByUserEmail(EMAIL_TEST1, null);
		Assert.assertNotNull(notes);
		Assert.assertEquals(3, notes.size());
	}

	@Test
	public void shouldReadUserByEmailAndFilters(){
		Map<String, Object> filterParams = new HashMap<String, Object>();
		final Integer noteID = Integer.valueOf(1);
		
		filterParams.put("id", noteID);
		List<Note> notes = this.noteRepository.getAllByUserEmail(EMAIL_TEST1, filterParams);
		
		Assert.assertNotNull(notes);
		Assert.assertEquals(1, notes.size());
		Assert.assertEquals(noteID, notes.get(0).getId());
	}
	

	@Test
	public void shouldCreateNote(){
		final int numLinks = 2;
		Note note = this.createNote(numLinks);
		
		this.noteRepository.saveOrUpdate(note);
		
		Assert.assertNotNull(note.getId());
		Assert.assertEquals(TITLE_NOTE_TEST, note.getTitle());
		Assert.assertEquals(CONTENT_NOTE_TEST, note.getContent());
		Assert.assertEquals(numLinks,  note.getLinks().size());
		
		List<Note> notes = this.noteRepository.getAllByUserEmail(EMAIL_TEST1, null);
		Assert.assertNotNull(notes);
		Assert.assertEquals(4, notes.size());
	}


	@Test
	public void shouldDeleteNote(){
		final int numNotesBeforeDelete = 3;
		
		List<Note> notes = this.noteRepository.getAllByUserEmail(EMAIL_TEST1, null);
		Assert.assertNotNull(notes);
		Assert.assertEquals(numNotesBeforeDelete, notes.size());
		
		
		Note noteToDelete = notes.get(1);
		this.noteRepository.delete(noteToDelete);
		
		List<Note> notesAfterDelete = noteRepository.getAllByUserEmail(EMAIL_TEST1, null);
		Assert.assertNotNull(notesAfterDelete);
		Assert.assertEquals((numNotesBeforeDelete - 1), notesAfterDelete.size());
		
		for (Note currentNode : notesAfterDelete){
			Assert.assertNotSame(noteToDelete, currentNode);
		}
	}
	
	
	@Test
	public void shouldUpdateNote(){
		final Integer noteID = Integer.valueOf(1);
		final String text_updated = "UPDATED";
		
		Note note = noteRepository.findByPK(Note.class, noteID);
		
		note.setTitle(text_updated);
		note.setContent(text_updated);
		
		this.noteRepository.saveOrUpdate(note);
		
		Note noteAfterUpdate = noteRepository.findByPK(Note.class, noteID);

		Assert.assertEquals(text_updated, noteAfterUpdate.getTitle());
		Assert.assertEquals(text_updated, noteAfterUpdate.getContent());
	}
	
	private Note createNote(int numLinks){
		User user = this.userRepository.getUserByEmail(EMAIL_TEST1);
		
		Date now  = new Date();
		Note note = new Note();
		note.setContent(CONTENT_NOTE_TEST);
		note.setTitle(TITLE_NOTE_TEST);
		note.setCreated(now);
		note.setOwner(user);
		
		for (int i = 0; i < numLinks; i++){
			Link link = new Link();
			link.setNote(note);
			link.setUrl(String.format("http://url_%d.com", i));
			note.addLink(link);
		}
		
		return note;
	}

}
