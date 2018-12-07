package com.escolar.exception;
public class EscolarException extends java.lang.RuntimeException {
	private static final long serialVersionUID = 1165450428216624538L;
	private Long id;
	private String exepcion;
	private String mensajeUsuario= this.getClass().getSimpleName()+" no se encontro "  ;
	public EscolarException(){
		super();
	}
	
	public EscolarException(String message){
		super(message);
	}
	
	public EscolarException(Long id){
		this.id = id;
	}
	public EscolarException(Long id,String exepcion){
		this.id = id;
		this.exepcion=exepcion;
	}
	public EscolarException(Long id,String exepcion,String mensajeUsuario){
		this.id = id;
		this.exepcion=exepcion;
		this.mensajeUsuario=mensajeUsuario;
	}

	public Long getId() {
		return id;
	}
	public String getExcepcion() {
		return exepcion;
	}

	@Override
	public String getMessage() {
		return mensajeUsuario;
	}
}