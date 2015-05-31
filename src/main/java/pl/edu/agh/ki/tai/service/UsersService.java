package pl.edu.agh.ki.tai.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.ki.tai.dao.UsersDao;
import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.model.User;

@Service("usersService")
public class UsersService {

	@Autowired
	private UsersDao usersDao;

	public void create(User user){
		usersDao.create(user);
	}
	
	public boolean exists(String username){
		return usersDao.exists(username);
	}
	
	public User getUserByName(String username){
		return usersDao.getUserByName(username);
	}

	public void updateGroups(String username, Group group){
		usersDao.updateGroups(username, group);
	}
	
	public boolean containsGroup(String username, String groupname){
		return usersDao.containsGroup(username, groupname);
	}
}
