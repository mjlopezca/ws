package es.carlosgarcia.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * A simple note
 * @author Carlos García
 * @see http://carlos-garcia.es
 */
public class Note {
	private Integer id;	
	private Date	created;
	private String	title;
	private String	content;
	private List<Link> links;
	private User owner;

	public Note(){
		super();
		this.links = new ArrayList<Link>();
	}
	
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
	
	public void setLinks(List<Link> links){
		this.links = links;
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
