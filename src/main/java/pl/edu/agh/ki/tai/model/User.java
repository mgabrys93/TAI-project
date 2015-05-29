package pl.edu.agh.ki.tai.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import pl.edu.agh.ki.tai.dao.FormValidationGroup;

@Entity
@Table(name = "users")
public class User {

	@NotBlank(groups={FormValidationGroup.class})
	@Size(min=5, max=15, groups={FormValidationGroup.class})
	@Pattern(regexp="^\\w{5,}$", groups={FormValidationGroup.class})
	@Id
	@Column(name = "username")
	private String username;
	
	@NotBlank(groups={FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", groups={FormValidationGroup.class})
	@Size(min=8, max=15, groups={FormValidationGroup.class})
	private String password;
	
	@NotBlank(groups={FormValidationGroup.class})
	@Email(groups={FormValidationGroup.class})
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Event> events;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
	private Set<Comment> comments;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
	private Set<UserRole> authorities = new HashSet<UserRole>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_group", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false)},
			inverseJoinColumns = {
				@JoinColumn(name = "group_id", nullable = false)
	})
	private Set<Group> groups;

	@Column(columnDefinition="tinyint", nullable=false)
	private boolean enabled = false;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<UserRole> authorities) {
		this.authorities = authorities;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
