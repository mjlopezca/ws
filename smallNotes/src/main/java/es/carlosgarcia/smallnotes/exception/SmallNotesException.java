package es.carlosgarcia.smallnotes.exception;

/**
 * Base SmallNote exception
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
public class SmallNotesException extends java.lang.RuntimeException {
	private static final long serialVersionUID = 1165450428216624538L;

	public SmallNotesException(){
		super();
	}
	
	public SmallNotesException(String message){
		super(message);
	}
}
