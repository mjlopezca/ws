package es.carlosgarcia.smallnotes.web.oauth2.api;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import es.carlosgarcia.smallnotes.exception.AccessDeniedException;
import es.carlosgarcia.smallnotes.exception.NoteNotFoundException;
import es.carlosgarcia.smallnotes.model.Link;
import es.carlosgarcia.smallnotes.model.Note;
import es.carlosgarcia.smallnotes.model.User;
import es.carlosgarcia.smallnotes.service.SmallNotesService;

/**
 * API protected by OAuth2
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@Controller
@RequestMapping(value = "/v1/")
public class SmallNotesAPI implements InitializingBean {
	private static final Logger logger  = LoggerFactory.getLogger(SmallNotesAPI.class);
	
	private SmallNotesService smallNotesService;
	
	public SmallNotesAPI(){
		super();
	}
	
	@Autowired
	public SmallNotesAPI(SmallNotesService smallNotesService){
		logger.debug("Creating SmallNotesAPIImpl");
		this.smallNotesService = smallNotesService;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Validate.notNull(this.smallNotesService);
	}
	
	/**
	 * @param filter If not null, filter to be applied. Example: q?title:titulo1,content:Contenido1
	 * @param fields If not null. Partial response. Example: ?fields=id,title
	 * @return Note list of the logged user.
	 */
	@RequestMapping(value = "/api/notes", method = RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_ADMINISTRADOR') and #oauth2.hasScope('read')")
	@ResponseBody 
	public List<Note> getNotes(@RequestParam(required=false, value="q") String filter, @RequestParam(required=false, value="fields") String fields) {
		logger.debug("SmallNotesAPIImpl.getNotes. filter {}, fields {}", filter, fields);
		
		User user = this.getLoggedUser();

		Map<String, Object> params = null;
		
		if (StringUtils.isNotBlank(filter)){
			params = SmallNotesApiHelper.parseFilter(filter);
		}
		
		List<Note> notes = this.smallNotesService.getAll(user, params);
		
		if (logger.isDebugEnabled()){
			int numNotes = 0;
			if (notes != null){
				numNotes = notes.size();
			}
			logger.debug("num notes that match filter {}", numNotes);
		}
		
		// ¿Is partial response requested?
		if (StringUtils.isNotBlank(fields) && (notes != null)){
			return SmallNotesApiHelper.applyPartialResponse(notes, fields);
		} else {
			return notes;
		}
	}
	

	/**
	 * Create a note.
	 * @param note Note to be created
	 */
	@RequestMapping(value = "/api/notes", method = RequestMethod.POST)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)
	public void createNote(@RequestBody Note note) {
		User user = this.getLoggedUser();
		note.setOwner(user);
		smallNotesService.create(note);
	}

	/**
	 * Delete all notes of user logged in.
	 * @return The number of notes deleted.
	 */
	@RequestMapping(value = "/api/notes", method = RequestMethod.DELETE)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)
	@ResponseBody
	public int deleteNotes() {
		User user = this.getLoggedUser();
		int  numDeleted = this.smallNotesService.deleteNotes(user);
		return numDeleted;
	}
	

	/**
	 * Get a note by id.
	 * @param noteId Note identify.
	 * @param fields If not null. Partial response. Example: ?fields=id,title
	 * @return A note.
	 */
	@RequestMapping(value = "/api/notes/{noteId}", method = RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('read')")
	@ResponseBody 
	public Note getNote(@PathVariable Integer noteId, @RequestParam(required=false, value="fields") String fields) {
		Note note = this.getUserNote(noteId);
		
		logger.debug("SmallNotesAPIImpl.getNote Id: {}", noteId);

		if (StringUtils.isBlank(fields)){
			return note;
		} else {
			return SmallNotesApiHelper.applyPartialResponse(note, fields);	
		}
	}

	/**
	 * Delete a note.
	 * @param noteId Code of the note to be deleted
	 */
	@RequestMapping(value = "/api/notes/{noteId}", method = RequestMethod.DELETE)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)
	public void deleteNote(@PathVariable Integer noteId) {
		Note note = this.getUserNote(noteId);
		if (note != null){
			this.smallNotesService.delete(note);
		}
	}

	/**
	 * Update a note.
	 * @param noteId Note identify.
	 * @param note Note content to be created
	 */
	@RequestMapping(value = "/api/notes/{noteId}", method = RequestMethod.PUT)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('write')")
	@ResponseStatus(value=HttpStatus.OK)
	public void updateNote(@PathVariable Integer noteId, @RequestBody Note note) {
		if ((note == null) || (noteId != note.getId())){
			throw new IllegalArgumentException("Invalid note");
		}
		
		// this method check that logger user is the owner of note
		Note notePersisted = this.getUserNote(noteId);

		note.setOwner(notePersisted.getOwner());
		
		this.smallNotesService.update(note);
	}   
	
	/**
	 * Get links of a note
	 * @param noteId Note code
	 * @return The links of the note.
	 */
	@RequestMapping(value = "/api/notes/{noteId}/links", method = RequestMethod.GET)
	@PreAuthorize("#oauth2.isUser() and #oauth2.clientHasRole('ROLE_USER') and #oauth2.hasScope('read')")
	@ResponseBody 
	public List<Link> getNoteLinks(@PathVariable Integer noteId) {
		Note note = this.getUserNote(noteId);
		
		logger.debug("SmallNotesAPIImpl.getNoteLinks Id: {}, user {}", noteId);
		
		return note.getLinks();
	}
	
	/**
	 * Error if user try to access to any other url.
	 */
	@RequestMapping(value = "/api/**", method = RequestMethod.GET)
	public void notExistsUrlHandler(HttpServletRequest request) {
		throw new IllegalArgumentException("Requested url not exists: " + request.getRequestURI());
	}
	
	/**
	 * Get a note validate that user is the owner.
	 * @param noteId Note code to get.
	 * @return Return the note
	 */
	private Note getUserNote(Integer noteId) {
		Note note = this.smallNotesService.getNote(noteId);
		
		if (note == null){
			throw new NoteNotFoundException(noteId);
		}
		
		User user = this.getLoggedUser();
		if (! user.equals(note.getOwner())){
		 	throw new AccessDeniedException(noteId);
		}
		
		return note;
	}
	
	/**
	 * @return The logged user
	 */
	private User getLoggedUser(){
		String email =  null;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object	   principal  = authentication.getPrincipal();
		
		
		if (principal instanceof UserDetails) {
			email =  ((UserDetails) principal).getUsername();
		} else if (principal instanceof UsernamePasswordAuthenticationToken){
			email = ((UsernamePasswordAuthenticationToken) principal).getName();
		} else if (principal instanceof OAuth2Authentication){
		    email = ((OAuth2Authentication) principal).getUserAuthentication().getName();
		}
		
		logger.debug("Logged user email {}", email);
		
		Validate.notNull(email);
		
		User user  = this.smallNotesService.getUserByEmail(email);

		logger.debug("Logged user {}", user);
		
		return user;
	}

	
	/**
	 * Exception Handlers
	 */
	
	@ExceptionHandler(NoteNotFoundException.class)
	protected @ResponseBody ResponseEntity<RestApiError> handleNoteNotFoundException(NoteNotFoundException noteNotFoundException, HttpServletRequest request, HttpServletResponse response) {
		RestApiError restApiError = new RestApiError(HttpStatus.NOT_FOUND, ApiErrorCode.NOTE_NOT_FOUND, noteNotFoundException.getMessage(), noteNotFoundException.getMessage(), this.getInfoUrl(ApiErrorCode.NOTE_NOT_FOUND));
		return SmallNotesApiHelper.createAndSendResponse(restApiError);
	}

	@ExceptionHandler(AccessDeniedException.class)
	protected @ResponseBody ResponseEntity<RestApiError> handleAccessDeniedException(AccessDeniedException accessDeniedException, HttpServletRequest request, HttpServletResponse response) {
		RestApiError restApiError = new RestApiError(HttpStatus.UNAUTHORIZED, ApiErrorCode.ACCESS_DENIED, accessDeniedException.getMessage(), accessDeniedException.getMessage(), this.getInfoUrl(ApiErrorCode.ACCESS_DENIED));
		return SmallNotesApiHelper.createAndSendResponse(restApiError);
	}   

	@ExceptionHandler(SecurityException.class)
	protected @ResponseBody ResponseEntity<RestApiError> handleSecurityException(SecurityException exception, HttpServletRequest request, HttpServletResponse response) {
		RestApiError restApiError = new RestApiError(HttpStatus.UNAUTHORIZED, ApiErrorCode.SECURITY, exception.getMessage(), exception.getMessage(), this.getInfoUrl(ApiErrorCode.SECURITY));
		return SmallNotesApiHelper.createAndSendResponse(restApiError);
	}
	
	@ExceptionHandler(Exception.class)
	protected  @ResponseBody  ResponseEntity<RestApiError> handleException(Exception exception, HttpServletRequest request, HttpServletResponse response) {
		RestApiError restApiError = new RestApiError(HttpStatus.BAD_REQUEST, ApiErrorCode.GENERIC, exception.getMessage(), exception.getMessage(), this.getInfoUrl(ApiErrorCode.GENERIC));
		return SmallNotesApiHelper.createAndSendResponse(restApiError);
	}
	
	private String getInfoUrl(ApiErrorCode code){
		return "http://yourAppUrlToDocumentedApiCodes.com/api/support/" + code.ordinal();
	}
}