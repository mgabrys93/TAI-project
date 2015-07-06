package pl.edu.agh.ki.tai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.edu.agh.ki.tai.service.TwitterService;

@Controller
public class TwitterController {

    @Autowired
    private TwitterService twitterService;

    @RequestMapping("/tweets/{hashtag}")
    public String showHashTags(@PathVariable String hashtag) {
    	SearchResults results = twitterService.getTweetMessages(hashtag);   	
    	for(Tweet tweet : results.getTweets()) {
    		System.out.println(tweet.getUser().getName());
    		System.out.println(tweet.getUser().getProfileUrl());
    	}        
       
        return "tweets";
    }
}
