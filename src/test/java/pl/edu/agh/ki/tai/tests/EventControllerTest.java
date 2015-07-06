package pl.edu.agh.ki.tai.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.bind.annotation.PathVariable;

import pl.edu.agh.ki.tai.controller.EventController;
import pl.edu.agh.ki.tai.model.Event;
import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.service.EventsService;
import pl.edu.agh.ki.tai.service.GroupsService;
import pl.edu.agh.ki.tai.service.UsersService;

public class EventControllerTest {
	ExtendedModelMap model = new ExtendedModelMap();
	@Test
	public void testShowNewEvent() throws Exception{
		EventController eventController = new EventController();
		ArrayList<Event> list = new ArrayList<Event>();
		list.add(new Event());
		EventsService es = mock(EventsService.class);
		when(es.getAllEvents()).thenReturn(list);
		eventController.setEventsService(es);
		assertEquals("group", eventController.showNewEvent(model, "Nazwa grupy"));
	}
	
	@Test
	public void testCreateNewEvent() throws Exception{
		EventController eventController = new EventController();
		Event event = new Event();
		Group group = new Group();
		User user = new User();
		EventsService es = mock(EventsService.class);
		GroupsService gs = mock(GroupsService.class);
		UsersService us = mock(UsersService.class);
		Principal principal = new Principal() {
			
			public String getName() {
				// TODO Auto-generated method stub
				return "username";
			}
		};
		eventController.setEventsService(es);
		eventController.setGroupsService(gs);
		eventController.setUsersService(us);
		
		ArrayList<Event> list = new ArrayList<Event>();
		list.add(new Event());
		
		when(es.getAllEvents()).thenReturn(list);
		when(gs.getGroupByName("Nazwa Grupy")).thenReturn(group);
		when(us.getUserByName("username")).thenReturn(user);
		Mockito.doNothing().when(es).create(event);
		
		assertEquals("redirect:group", eventController.createNewEvent(event, "Nazwa Grupy", principal));
		assertEquals("redirect:group", eventController.createNewEvent(event, "Nazwa Grupy", null));
	}
		
}
