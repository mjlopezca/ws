package es.carlosgarcia.smallnotes.exception;

/**
 * Note not found exception.
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
public class NoteNotFoundException extends SmallNotesException {
	private static final long serialVersionUID = 4357294954650505721L;
	
	private Integer noteId;
	
	public NoteNotFoundException(Integer noteId){
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
