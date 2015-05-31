package pl.edu.agh.ki.tai.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.ki.tai.model.Group;

@Repository("groupsDao")
@Transactional
public class GroupsDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(Group group){
		session().save(group);
	}
	
	public boolean exists(String groupname){
		Criteria criteria = session().createCriteria(Group.class);
		criteria.add(Restrictions.eq("groupname", groupname));
		Group group = (Group) criteria.uniqueResult();
		return (group != null) ? true : false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroup(){
		Criteria criteria = session().createCriteria(Group.class);
		return criteria.list();
	}
	
	public Group getGroupByName(String groupname){
		Criteria criteria = session().createCriteria(Group.class);
		criteria.add(Restrictions.eq("groupname", groupname));
		return (Group) criteria.uniqueResult();
	}
}
