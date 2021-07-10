package bg.sofia.uni.fmi.mjt.socialmedia.user;

import bg.sofia.uni.fmi.mjt.socialmedia.content.*;

import java.time.LocalDateTime;
import java.util.*;

public class User implements ActiveUser {
    private String name;

    private Map<String, Publication> publications;
    private Set<Pair<Activity, LocalDateTime>> activities;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String publishPost(LocalDateTime publishedOn, String description) {
        Publication post = new Post(this, publishedOn);
        publications.put(post.getId(), post);
        return post.getId();
    }

    @Override
    public String publishStory(LocalDateTime publishedOn, String description) {
        Publication story = new Story(this, publishedOn);
        publications.put(story.getId(), story);
        return story.getId();
    }

    @Override
    public void comment(String text, String id) {
        Activity comment = new Comment(id, text);
        activities.add(new Pair(comment, LocalDateTime.now()));
    }

    @Override
    public void like(String Id) {
        Activity like = new Like(Id);
        activities.add(new Pair(like, LocalDateTime.now()));
    }

    public Publication getPublication(String Id) {
        return publications.get(Id);
    }

    public Collection<Publication> getPublications() {
        Collection<Publication> publications = new ArrayList(this.publications.size());
        Set<Map.Entry<String, Publication>> entriesOfPublications = this.publications.entrySet();
        for (Map.Entry<String, Publication> entry : entriesOfPublications) {
            publications.add(entry.getValue());
        }
        return publications;
    }
}
