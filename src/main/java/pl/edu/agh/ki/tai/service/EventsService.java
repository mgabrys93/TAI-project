package pl.edu.agh.ki.tai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.ki.tai.dao.EventsDao;
import pl.edu.agh.ki.tai.model.Event;

@Service
public class EventsService {

	@Autowired
	private EventsDao eventsDao;

	public void create(Event event){
		eventsDao.create(event);
	}
	
	public List<Event> getAllEvents(){
		return eventsDao.getAllEvents();
	}
	
	public Event getEventById(Long id){
		return eventsDao.getEventById(id);
	}
}
