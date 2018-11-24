package es.carlosgarcia.external.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.carlosgarcia.core.model.Note;
import es.carlosgarcia.external.service.SmallNotesService;

@Controller
public class SmallNotesOauth2Controller {
	private static final Logger logger  = LoggerFactory.getLogger(SmallNotesOauth2Controller.class);
	
	private SmallNotesService smallNotesService;
	
	@Autowired
	public SmallNotesOauth2Controller(SmallNotesService smallNotesService){
		this.smallNotesService = smallNotesService;
	}
	
	@RequestMapping(value = "/oauth/api/notes", method = RequestMethod.GET)
	public final String apiNotes(ModelMap map) {
		logger.debug("Quering notes API (OAuth2 Autentication needed)");
		
		List<Note> notes   = smallNotesService.doRequestApiNotes();
		map.addAttribute("notes", notes);
		
		return AppConstants.HOME_VIEW;
	}
	

	@RequestMapping(value = "/oauth/api/notes/{noteId}", method = RequestMethod.GET)
	public final String apiGetNote(ModelMap map, @PathVariable("noteId") String noteId) {
		logger.debug("Quering Note API (OAuth2 Autentication needed), of note {}", noteId);
		
		Note note = smallNotesService.doRequestApiNote(noteId);
		map.addAttribute("note", note);
		
		return AppConstants.HOME_VIEW;
	}
	
	@RequestMapping(value = "/oauth/api/notes/{noteId}", method = RequestMethod.DELETE)
	public final String apiDeleteNote(@PathVariable("noteId") String noteId) {
		logger.debug("Delete Note API (OAuth2 Autentication needed), of note {}", noteId);
		
		try {
			smallNotesService.doDeleteRequestApiNote(noteId);
		} catch (Exception ex){
			logger.warn("deleting problems", ex);
		}
		
		return AppConstants.HOME_VIEW;
	}
	
}
