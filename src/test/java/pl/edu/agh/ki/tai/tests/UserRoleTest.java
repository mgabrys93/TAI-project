package pl.edu.agh.ki.tai.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.model.UserRole;

public class UserRoleTest {


@Test
public void testUserRole() throws Exception{
UserRole userRole = new UserRole();
assertEquals(null,userRole.getRole());

userRole = new UserRole("role");
assertEquals("role",userRole.getRole());

User user = new User();
userRole = new UserRole(user, "role");
assertEquals("role",userRole.getRole());
assertEquals(user,userRole.getUser());

userRole.setId(0);
assertEquals(0, userRole.getId());

userRole.setRole("role");
assertEquals("role", userRole.getRole());

userRole.setUser(user);
assertEquals(user,userRole.getUser());

}

}