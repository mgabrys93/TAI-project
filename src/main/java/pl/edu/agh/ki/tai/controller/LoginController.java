package pl.edu.agh.ki.tai.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.ki.tai.dao.FormValidationGroup;
import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.model.UserRole;
import pl.edu.agh.ki.tai.service.UsersService;

@Controller
public class LoginController {

	@Autowired
	private UsersService usersService;

	@RequestMapping("/home")
	public String showHomepage(Model model, Principal principal){
		String username = "";
		if(principal != null){
			username = principal.getName();
		}
		model.addAttribute("username", username);
		return "home";
	}
	
	@RequestMapping("/login")
	public String showLogin(){
		return "login";
	}

	@RequestMapping("/denied")
	public String showDenied(){
		return "denied";
	}
	
	@RequestMapping("/loggedout")
	public String showLoggedOut(){
		return "loggedout";
	}
	
	@RequestMapping("/newaccount")
	public String showNewAccount(Model model){
		model.addAttribute("user", new User());
		return "newaccount";
	}
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST)
	public String createAccount(@Validated(FormValidationGroup.class)User user, BindingResult  result){
		if(result.hasErrors()){
			return "newaccount";
		}
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole("ROLE_USER");
		
		user.getAuthorities().add(userRole);
		user.getAuthorities().add(new UserRole(user, "ROLE_ADMIN"));
		user.setEnabled(true);
		
		if(usersService.exists(user.getUsername())){
			result.rejectValue("username", "DuplicateKey.user.username");
			return "newaccount";
		}
		usersService.create(user);
		return "accountcreated";
	}
}
