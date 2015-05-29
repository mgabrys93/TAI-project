package pl.edu.agh.ki.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.ki.tai.dao.UsersDao;
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
}
