package es.carlosgarcia.smallnotes.exception;

/**
 * User has not privilege with resource
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
public class AccessDeniedException extends SmallNotesException {
	private static final long serialVersionUID = 4357294954650505721L;
	
	private Integer noteId;
	
	public AccessDeniedException(Integer noteId){
		this.noteId = noteId;
	}

	public Integer getNoteId() {
		return noteId;
	}

	@Override
	public String getMessage() {
		return this.getClass().getSimpleName() + " " + noteId;
	}
	
}
