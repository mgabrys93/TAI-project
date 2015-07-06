package pl.edu.agh.ki.tai.tests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.assertEquals;

import java.security.Principal;

import org.junit.Test;
import org.springframework.ui.ExtendedModelMap;

import org.springframework.validation.BindingResult;

import pl.edu.agh.ki.tai.controller.LoginController;
import pl.edu.agh.ki.tai.controller.SimpleController;
import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.service.UsersService;

public class LoginControllerTest {

	LoginController loginController = new LoginController();
	ExtendedModelMap model = new ExtendedModelMap();
	SimpleController simpleController = new SimpleController();

	@Test
	public void testHelloWorld() throws Exception {
		assertEquals("index", simpleController.helloWorld(model));
	}

	@Test
	public void testShowHomepage() throws Exception {
		Principal principle = new Principal() {

			public String getName() {
				return "username";
			}
		};
		assertEquals("home", loginController.showHomepage(model, principle));
		assertEquals("home", loginController.showHomepage(model, null));
	}

	@Test
	public void testShowLogin() throws Exception {
		assertEquals("login", loginController.showLogin());
	}

	@Test
	public void testShowDenied() throws Exception {
		assertEquals("denied", loginController.showDenied());

	}

	@Test
	public void testShowLoggedOut() {
		assertEquals("loggedout", loginController.showLoggedOut());
	}

	@Test
	public void testShowNewAccount() {
		assertEquals("newaccount", loginController.showNewAccount(model));
	}

	@Test
	public void testCreateAccount() {
		User user = new User();
		BindingResult result = mock(BindingResult.class);
		when(result.hasErrors()).thenReturn(true);
		assertEquals("newaccount", loginController.createAccount(user, result));

		UsersService us = mock(UsersService.class);
		when(us.exists(user.getUsername())).thenReturn(false);
		when(result.hasErrors()).thenReturn(false);
		loginController.setUSersService(us);

		assertEquals("accountcreated",
				loginController.createAccount(user, result));

		when(us.exists(user.getUsername())).thenReturn(true);
		assertEquals("newaccount", loginController.createAccount(user, result));
	}
}