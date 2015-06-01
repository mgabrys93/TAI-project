package pl.edu.agh.ki.tai.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.edu.agh.ki.tai.model.Comment;
import pl.edu.agh.ki.tai.model.Event;
import pl.edu.agh.ki.tai.model.User;
import pl.edu.agh.ki.tai.service.CommentsService;
import pl.edu.agh.ki.tai.service.EventsService;
import pl.edu.agh.ki.tai.service.UsersService;

@Controller
public class CommentController {

	@Autowired
	private EventsService eventsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CommentsService commentsService;
	
	@RequestMapping(value="/event/{eventId}/comment")
	public String showComment(Model model, @PathVariable("eventId") String eventId){
		model.addAttribute("event", eventsService.getEventById(Long.valueOf(eventId)));
		model.addAttribute("commentList", commentsService.getAllComments(Long.valueOf(eventId)));
		model.addAttribute("comment", new Comment());
		return "event";
	}
	
	@RequestMapping(value="/event/{eventId}/comment", method=RequestMethod.POST)
	public String createComment(Comment comment, @PathVariable("eventId") String eventId, Principal principal ) {
		
		String username = "";
		if(principal != null){
			username = principal.getName();
		}

		Event event = eventsService.getEventById(Long.valueOf(eventId));
		User user = usersService.getUserByName(username);
		
		comment.setAuthor(user);
		comment.setEvent(event);
		
		commentsService.create(comment);
		return "redirect:comment";
	}
}
