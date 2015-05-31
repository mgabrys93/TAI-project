package pl.edu.agh.ki.tai.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.edu.agh.ki.tai.dao.FormValidationGroup;
import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.service.GroupsService;
import pl.edu.agh.ki.tai.service.UsersService;

@Controller
public class GroupController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private GroupsService groupsService;
	
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
		
		Set<Group> groups = new HashSet<Group>();
		groups.add(group);
		
		if(groupsService.exists(group.getGroupname())){
			result.rejectValue("groupname", "DuplicateKey.group.groupname");
			return "newgroup";
		}
		
		groupsService.create(group);
		usersService.updateGroups(username, groups);
		
		return "groupcreated";
	}
}
