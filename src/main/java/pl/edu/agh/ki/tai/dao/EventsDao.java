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

import pl.edu.agh.ki.tai.model.Event;

@Repository("eventsDao")
@Transactional
public class EventsDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void create(Event event){
		session().save(event);
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getAllEvents(){
		Criteria criteria = session().createCriteria(Event.class);
		return criteria.list();
	}
	
	public Event getEventById(Long id){
		Criteria criteria = session().createCriteria(Event.class);
		criteria.add(Restrictions.eq("eventId", id));
		return (Event) criteria.uniqueResult();
	}
}
