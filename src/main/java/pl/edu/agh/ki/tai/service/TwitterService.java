package pl.edu.agh.ki.tai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Service;

@Service("twitterService")
public class TwitterService {

    @Autowired
    private Twitter twitter;

    public SearchResults getTweetMessages(String hashtag){
        return twitter.searchOperations().search("#" + hashtag);
    }

}
