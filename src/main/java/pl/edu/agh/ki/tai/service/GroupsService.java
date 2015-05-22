package pl.edu.agh.ki.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.ki.tai.dao.GroupsDao;
import pl.edu.agh.ki.tai.model.Group;

@Service("groupsService")
public class GroupsService {

	@Autowired
	private GroupsDao groupsDao;

	public void create(Group group){
		groupsDao.create(group);
	}
}
