package pl.edu.agh.ki.tai.dao;

import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.ki.tai.model.Group;
import pl.edu.agh.ki.tai.model.User;

@Repository("usersDao")
@Transactional
public class UsersDao {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(User user){
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		session().save(user);
	}

	public boolean exists(String username){
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		User user = (User) criteria.uniqueResult();
		return (user != null) ? true : false;
	}
	
	public User getUserByName(String username){
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}
	
	public void updateGroups(String username, Group group){
		User user = getUserByName(username);
		user.getGroups().add(group);
		session().update(user);
	}
	
	public boolean containsGroup(String username, String groupname){
		Criteria criteria = session().createCriteria(User.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.createAlias("groups", "g");
		criteria.add(Restrictions.eq("g.groupname", groupname));
		User user = (User) criteria.uniqueResult();
		return (user != null) ? true : false;
	}
}
