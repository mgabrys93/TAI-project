package pl.edu.agh.ki.tai.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.List;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.edu.agh.ki.tai.controller.EventController;
import pl.edu.agh.ki.tai.controller.GroupController;
import pl.edu.agh.ki.tai.model.Event;
import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.service.GroupsService;
import pl.edu.agh.ki.tai.service.UsersService;

public class GroupControllerTest {

	GroupController groupController = new GroupController();
	ExtendedModelMap model = new ExtendedModelMap();

	@Test
	public void testShowNewGroup() throws Exception {
		assertEquals("newgroup", groupController.showNewGroup(model));
	}

	@Test
	public void testShowJoinToGroup() throws Exception {
		ArrayList<String> list = new ArrayList<String>();
		list.add("Grupa 1");
		GroupsService gs = mock(GroupsService.class);
		when(gs.getAllGroupNames()).thenReturn(list);

		groupController.setGrupService(gs);
		assertEquals("jointogroup", groupController.showJoinToGroup(model));
	}

	@Test
	public void testCreateoGroup() throws Exception {
		Group group = new Group();
		GroupsService gs = mock(GroupsService.class);
		BindingResult result = mock(BindingResult.class);
		UsersService us = mock(UsersService.class);
		Principal principal = new Principal() {

			public String getName() {
				return "username";
			}
		};

		when(result.hasErrors()).thenReturn(true);
		assertEquals("newgroup", groupController.createGroup(group, result, principal));

		when(gs.exists(group.getGroupname())).thenReturn(true);
		when(result.hasErrors()).thenReturn(false);
		groupController.setGrupService(gs);
		assertEquals("newgroup", groupController.createGroup(group, result, principal));

		assertEquals("newgroup", groupController.createGroup(group, result, null));

		when(gs.exists(group.getGroupname())).thenReturn(false);

		Mockito.doNothing().when(gs).create(group);
		Mockito.doNothing().when(us).updateGroups(principal.getName(), group);
		groupController.setUSersService(us);

		assertEquals("groupcreated", groupController.createGroup(group, result, principal));
	}

	@Test
	public void testJoinToGroup() {
		Group group = new Group();
		GroupsService gs = mock(GroupsService.class);
		BindingResult result = mock(BindingResult.class);
		UsersService us = mock(UsersService.class);
		Principal principal = new Principal() {

			public String getName() {
				return "username";
			}
		};
		RedirectAttributes redirectAttributes = new RedirectAttributes() {

			public boolean containsAttribute(String arg0) {
				// TODO Auto-generated method stub
				return false;
			}

			public Map<String, Object> asMap() {
				// TODO Auto-generated method stub
				return null;
			}

			public Model addAllAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public RedirectAttributes mergeAttributes(Map<String, ?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public Map<String, ?> getFlashAttributes() {
				// TODO Auto-generated method stub
				return null;
			}

			public RedirectAttributes addFlashAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public RedirectAttributes addFlashAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public RedirectAttributes addAttribute(String arg0, Object arg1) {
				// TODO Auto-generated method stub
				return null;
			}

			public RedirectAttributes addAttribute(Object arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			public RedirectAttributes addAllAttributes(Collection<?> arg0) {
				// TODO Auto-generated method stub
				return null;
			}
		};

		groupController.setGrupService(gs);
		groupController.setUSersService(us);
		when(result.hasErrors()).thenReturn(true);
		assertEquals("jointogroup", groupController.joinToGroup(group, result, principal, redirectAttributes));

		when(result.hasErrors()).thenReturn(false);
		when(us.containsGroup(principal.getName(), group.getGroupname())).thenReturn(true);
		Mockito.doNothing().when(result).rejectValue("groupname", "Duplicate.user.groups");
		assertEquals("redirect:jointogroup", groupController.joinToGroup(group, result, principal, redirectAttributes));
		assertEquals("redirect:jointogroup", groupController.joinToGroup(group, result, null, redirectAttributes));

		when(us.containsGroup(principal.getName(), group.getGroupname())).thenReturn(false);
		Mockito.doNothing().when(us).updateGroups(principal.getName(), group);
		assertEquals("redirect:jointogroup", groupController.joinToGroup(group, result, principal, redirectAttributes));
	}

	// public String showMyGroups(Model model, Principal principal){
	//
	// String username = "";
	// if(principal != null){
	// username = principal.getName();
	// }
	// model.addAttribute("groupList",
	// usersService.getGroupsByUsername(username));
	//
	// return "grouplist";
	// }

	@Test
	public void testShowMyGroups() {
		GroupController gs = new GroupController();
		ArrayList<Group> list = new ArrayList<Group>();
		list.add(new Group());

		Principal principal = new Principal() {
			public String getName() {
				// TODO Auto-generated method stub
				return "username";
			}
		};
		UsersService us = mock(UsersService.class);
		when(us.getGroupsByUsername("username")).thenReturn(list);
		gs.setUSersService(us);

		assertEquals("grouplist", gs.showMyGroups(model, principal));
	}
}
