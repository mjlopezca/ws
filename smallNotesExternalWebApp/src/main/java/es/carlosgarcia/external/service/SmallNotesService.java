package es.carlosgarcia.external.service;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import es.carlosgarcia.core.model.Note;

@Service
public class SmallNotesService {
	private static final Logger logger  = LoggerFactory.getLogger(SmallNotesService.class);
	private String		 requestBaseApiNoteUrl;
	private RestOperations restTemplate;

	@Autowired
	public SmallNotesService(String requestBaseApiNoteUrl, RestOperations restTemplate){
		logger.debug("SmallNotesService with base API Note Url {}", requestBaseApiNoteUrl);
		this.requestBaseApiNoteUrl = requestBaseApiNoteUrl;
		this.restTemplate 		   = restTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<Note> doRequestApiNotes()  {
		logger.debug("doRequestApiNotes");

		try {
			List<Note> notes = restTemplate.getForObject(URI.create(requestBaseApiNoteUrl), List.class);
	
			if (notes != null) {
				logger.debug("Num notes received {}", notes.size());
			}
	
			return notes;
		} catch (RestClientException ex){
			logger.debug("RestClientException", ex);
			
			return null;
		}
	}
	
	public Note doRequestApiNote(String noteId)   {
		logger.debug("doRequestApiNote with noteId {}", noteId);

		try {
			Note note = restTemplate.getForObject(this.requestBaseApiNoteUrl + "/{noteId}",  Note.class, Integer.valueOf(noteId));
	
			logger.debug("doRequestApiNote received {}", note);
	
			return note;
		} catch (RestClientException ex){
			logger.debug("RestClientException", ex);
			return null;
		}
	}
	
	public void doDeleteRequestApiNote(String noteId)   {
		logger.debug("doDeleteRequestApiNote with noteId {}", noteId);

		restTemplate.delete(this.requestBaseApiNoteUrl + "/{noteId}", Integer.valueOf(noteId));
	}
}