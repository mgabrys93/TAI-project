package pl.edu.agh.ki.tai.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import pl.edu.agh.ki.tai.dao.FormValidationGroup;

@Entity
@Table(name = "groups")
public class Group {
	
	@NotBlank(message="{NotBlank.group.groupname}")
	@Id
	@Column(name = "groupname")
	private String groupname;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "groups")
	private Set<User> users;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
	private Set<Event> event;

	public Group(){
		
	}
	
	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Event> getEvent() {
		return event;
	}

	public void setEvent(Set<Event> event) {
		this.event = event;
	}

}
