package pl.edu.agh.ki.tai.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private long userId;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Event> events;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Comment> comments;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_group", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "group_id", nullable = false)
	})
	private Set<Group> groups;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	
}
