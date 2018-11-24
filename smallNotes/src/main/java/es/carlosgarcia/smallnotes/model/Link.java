package es.carlosgarcia.smallnotes.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Index;

/**
 * A link of a note
 * @author Carlos García. 
 * @see http://carlos-garcia.es
 */
@Entity
@Table(name="links")
public class Link extends TransferObject {

	private static final long serialVersionUID = -3307661674519002128L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	
	@Basic(optional=false)
	@Index(name = "linkURLIdx")
	private String	url;
	
	@ManyToOne(targetEntity = Note.class, optional=false)
	@JoinColumn(name = "noteId")
	private Note note;
	
	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonIgnore
	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
