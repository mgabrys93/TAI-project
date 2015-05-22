package pl.edu.agh.ki.tai.service;

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
}
