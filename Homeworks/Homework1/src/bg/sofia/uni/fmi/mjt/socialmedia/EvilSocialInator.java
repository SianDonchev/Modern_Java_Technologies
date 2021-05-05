package bg.sofia.uni.fmi.mjt.socialmedia;

import bg.sofia.uni.fmi.mjt.socialmedia.content.Content;
import bg.sofia.uni.fmi.mjt.socialmedia.exceptions.UsernameAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.socialmedia.user.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvilSocialInator implements SocialMediaInator {
    private Map<String, User> users;
    private Map<String, String> contentHolder;

    private void addUser(String username) {
        User newUser = new User(username);
        users.put(username, newUser);
    }

    @Override
    public void register(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username is null");
        }
        if (users.containsKey(username)) {
            throw new UsernameAlreadyExistsException("User with that name already exist");
        }

        addUser(username);
    }

    @Override
    public String publishPost(String username, LocalDateTime publishedOn, String description) {
        if (username == null || publishedOn == null || description == null) {
            throw new IllegalArgumentException("Username, publish date or description is null");
        }
        User userToPublish = users.get(username);
        if (userToPublish == null) {
            throw new IllegalArgumentException("Username not found");
        }

        return null;
    }

    @Override
    public String publishStory(String username, LocalDateTime publishedOn, String description) {
        return null;
    }

    @Override
    public void like(String username, String id) {

    }

    @Override
    public void comment(String username, String text, String id) {

    }

    @Override
    public Collection<Content> getNMostPopularContent(int n) {
        return null;
    }

    @Override
    public Collection<Content> getNMostRecentContent(String username, int n) {
        return null;
    }

    @Override
    public String getMostPopularUser() {
        return null;
    }

    @Override
    public Collection<Content> findContentByTag(String tag) {
        return null;
    }

    @Override
    public List<String> getActivityLog(String username) {
        return null;
    }
}
