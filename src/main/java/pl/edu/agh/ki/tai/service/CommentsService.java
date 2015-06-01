package pl.edu.agh.ki.tai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.agh.ki.tai.dao.CommentsDao;
import pl.edu.agh.ki.tai.model.Comment;

@Service("commentsService")
public class CommentsService {

	@Autowired
	private CommentsDao commentsDao;

	public void create(Comment comment){
		commentsDao.create(comment);
	}
	
	public List<Comment> getAllComments(Long eventId){
		return commentsDao.getAllComments(eventId);
	}
	
}
