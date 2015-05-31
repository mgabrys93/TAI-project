package pl.edu.agh.ki.tai.service;

import java.util.ArrayList;
import java.util.List;

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
	
	public boolean exists(String groupname){
		return groupsDao.exists(groupname);
	}

	public List<Group> getAllGroup(){
		return groupsDao.getAllGroup();
	}
	
	public List<String> getAllGroupNames(){
		List<String> ret = new ArrayList<String>();
		for(Group g : getAllGroup()){
			ret.add(g.getGroupname());
		}
		return ret;
	}
	
	public Group getGroupByName(String groupname){
		return groupsDao.getGroupByName(groupname);
	}
}
