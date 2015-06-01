package pl.edu.agh.ki.tai.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.ki.tai.model.Event;
import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.service.EventsService;
import pl.edu.agh.ki.tai.service.GroupsService;
import pl.edu.agh.ki.tai.service.UsersService;

@Controller
public class EventController {

	@Autowired
	private EventsService eventsService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
	public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	public void setGroupsService(GroupsService groupsService) {
		this.groupsService = groupsService;
	}
	
	@RequestMapping(value="/group/{groupname}")
	public String showNewEvent(Model model, @PathVariable("groupname") String groupname){
		model.addAttribute("event", new Event());
		model.addAttribute("eventList", eventsService.getAllEvents());
		return "group";
	}
	
	@RequestMapping(value="/group/{groupname}", method=RequestMethod.POST)
	public String createNewEvent(Event event, @PathVariable("groupname") String groupname, Principal principal){
		
		String username = "";
		if(principal != null){
			username = principal.getName();
		}
		
		Group group = groupsService.getGroupByName(groupname);
		User user = usersService.getUserByName(username);
		
		event.setAuthor(user);
		event.setGroup(group);
		
		eventsService.create(event);
		
		return "redirect:group";
	}
	
}
