package pl.edu.agh.ki.tai.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.agh.ki.tai.model.Comment;

@Repository("commentsDao")
@Transactional
public class CommentsDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(Comment comment) {
		session().save(comment);
	}
	
	@SuppressWarnings("unchecked")
	public List<Comment> getAllComments(Long eventId){
		Criteria criteria = session().createCriteria(Comment.class);
		criteria.createAlias("event", "e");
		criteria.add(Restrictions.eq("e.eventId", eventId));
		Set<Comment> set = new HashSet<Comment>(criteria.list());
		List<Comment> list = new ArrayList<Comment>();
		list.addAll(set);
		return list;
	}
}
