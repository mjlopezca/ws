package es.carlosgarcia.smallnotes.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * A simple note
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
@Entity
@Table(name="notes")
@JsonSerialize(include = Inclusion.NON_DEFAULT)
public class Note extends TransferObject {
	private static final long serialVersionUID = 4608706957805037954L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date	created;
	
	@Column(nullable=false)
	private String	title;
	
	@Column(nullable=false)
	private String	content;
	
	@OneToMany(mappedBy = "note", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Link> links;
	
	@ManyToOne(targetEntity = User.class, optional=false)
	@JoinColumn(name = "owner")
	private User owner;

	public Note(){
		super();
		this.links = new ArrayList<Link>();
	}
	
	@Override
	public Integer getId() {
		return this.id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	
	@JsonIgnore
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public void addLink(Link link){
		this.links.add(link);
	}
	
	public void deleteLink(Link link){
		this.links.remove(link);
	}	
	public List<Link> getLinks(){
		return java.util.Collections.unmodifiableList(this.links);
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
	
	public void setLinks(List<Link> links){
		this.links = links;
	}
}
