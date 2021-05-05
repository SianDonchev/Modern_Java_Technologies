package bg.sofia.uni.fmi.mjt.socialmedia.user;

import bg.sofia.uni.fmi.mjt.socialmedia.content.Activity;
import bg.sofia.uni.fmi.mjt.socialmedia.content.Content;
import bg.sofia.uni.fmi.mjt.socialmedia.content.Pair;
import bg.sofia.uni.fmi.mjt.socialmedia.content.Post;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class User implements ActiveUser {
    private String name;

    private Map<String, Pair<Content, LocalDateTime>> publications;
    private Set<Pair<Activity, LocalDateTime>> activities;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void publishPost(LocalDateTime publishedOn, String description) {
        Content post = new Post(this, publishedOn);
    }

    @Override
    public void publishStory(LocalDateTime publishedOn, String description) {

    }

    @Override
    public void comment(String text, String id) {

    }
}
