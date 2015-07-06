package pl.edu.agh.ki.tai.controller;

import java.security.Principal;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.service.GroupsService;
import pl.edu.agh.ki.tai.service.UsersService;

@Controller
public class GroupController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
	public void setGrupService(GroupsService gs){
		this.groupsService = gs;
	}
	
	public void setUSersService(UsersService us){
		this.usersService = us;
	}
	
	@RequestMapping(value="/newgroup")
	public String showNewGroup(Model model){
		model.addAttribute("group", new Group());
		return "newgroup";
	}
	
	
	@RequestMapping(value="/creategroup", method=RequestMethod.POST)
	public String createGroup(@Valid Group group , BindingResult result,
			Principal principal){
		if(result.hasErrors()){
			return "newgroup";
		}
		
		String username = "";
		if(principal != null){
			username = principal.getName();
		}
		
		if(groupsService.exists(group.getGroupname())){
			result.rejectValue("groupname", "DuplicateKey.group.groupname");
			return "newgroup";
		}
		
		groupsService.create(group);
		usersService.updateGroups(username, group);
		
		return "groupcreated";
	}
	
	@RequestMapping(value="/jointogroup", method=RequestMethod.GET)
	public String showJoinToGroup(Model model) {
		model.addAttribute("group", new Group());
		model.addAttribute("groups", groupsService.getAllGroupNames());
		return "jointogroup";
	}
	
	@RequestMapping(value="/jointogroup", method=RequestMethod.POST)
	public String joinToGroup(@Valid Group group, BindingResult result,
			Principal principal, RedirectAttributes redirectAttributes){
		if(result.hasErrors()){ 
			return "jointogroup";
		}
		
		String username = "";
		if(principal != null){
			username = principal.getName();
		}
		System.out.println(username);
		if(usersService.containsGroup(username, group.getGroupname())){
			result.rejectValue("groupname", "Duplicate.user.groups");
			redirectAttributes.addFlashAttribute("message", "you belong to this group");
			return "redirect:jointogroup";
		}
		
		usersService.updateGroups(username, group);
		redirectAttributes.addFlashAttribute("message", "joined successfully");
		
		return "redirect:jointogroup";
		
	}
	
	@RequestMapping(value="/groups")
	public String showMyGroups(Model model, Principal principal){
		
		String username = "";
		if(principal != null){
			username = principal.getName();
		}
		model.addAttribute("groupList", usersService.getGroupsByUsername(username));
		
		return "grouplist";
	}
}
