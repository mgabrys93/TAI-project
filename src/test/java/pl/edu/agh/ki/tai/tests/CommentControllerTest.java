package pl.edu.agh.ki.tai.tests;

import static org.junit.Assert.assertEquals;

import java.security.Principal;
import java.util.ArrayList;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import pl.edu.agh.ki.tai.controller.CommentController;
import pl.edu.agh.ki.tai.model.Comment;
import pl.edu.agh.ki.tai.model.Event;
import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.service.CommentsService;
import pl.edu.agh.ki.tai.service.EventsService;
import pl.edu.agh.ki.tai.service.UsersService;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CommentControllerTest {
	ExtendedModelMap model = new ExtendedModelMap();
	
	
	@Test
	public void testShowComment() {
		CommentsService commentsService = mock(CommentsService.class);
		EventsService eventsService = mock(EventsService.class);
		ArrayList<Comment> comments = new ArrayList<Comment>();
		comments.add(new Comment());
		Event event = new Event();
		CommentController commentController = new CommentController();
		commentController.setCommentsService(commentsService);
		commentController.setEventsService(eventsService);
		
		when(eventsService.getEventById(Long.valueOf("12345"))).thenReturn(event);
		when(commentsService.getAllComments(Long.valueOf("12345"))).thenReturn(comments);
		

		assertEquals("event", commentController.showComment(model, "12345"));
	}
	
	@Test
	public void testCreateComment(){
		CommentsService commentsService = mock(CommentsService.class);
		EventsService eventsService = mock(EventsService.class);
		UsersService usersService = mock(UsersService.class);
		Comment comment = new Comment();
		User user = new User();
		Event event = new Event();
		Principal principal = new Principal() {
			
			public String getName() {
				// TODO Auto-generated method stub
				return "username";
			}
		};
		CommentController commentController = new CommentController();
		commentController.setCommentsService(commentsService);
		commentController.setEventsService(eventsService);
		commentController.setUsersService(usersService);

		when(eventsService.getEventById(Long.valueOf("12345"))).thenReturn(event);
		when(usersService.getUserByName("username")).thenReturn(user);
		Mockito.doNothing().when(commentsService).create(comment);
		
		assertEquals("redirect:comment", commentController.createComment(comment, "12345", principal));
		assertEquals("redirect:comment", commentController.createComment(comment, "12345", null));
	}
	
	
	
}
