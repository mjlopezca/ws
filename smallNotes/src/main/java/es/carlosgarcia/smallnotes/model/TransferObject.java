package es.carlosgarcia.smallnotes.model;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Base class for all our entities.
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
public abstract class TransferObject implements java.io.Serializable {
	private static final long serialVersionUID = 8057933418068600701L;
	public abstract Integer getId();
	public abstract void setId(Integer id);
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
	public abstract String toString();
	
	@JsonIgnore	
	public boolean isNew(){
		Integer identity = getId();
		return ((identity == null) || (identity.intValue() == 0));
	}
	
	@JsonIgnore
	public boolean getNew(){
		return isNew();
	}
}
