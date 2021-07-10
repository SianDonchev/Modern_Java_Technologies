package bg.sofia.uni.fmi.mjt.socialmedia.content;

import bg.sofia.uni.fmi.mjt.socialmedia.user.User;

import java.time.LocalDateTime;
import java.util.Collection;

public abstract class Publication implements Content {
    private User ownerOfPublication;
    private int numberOfLikes;
    private String id;
    private LocalDateTime publishedOn;
    private Collection<String> tags;
    private Collection<String> mentions;
    private Collection<Comment> comments;

    private static int IdGenerator = 0;

    public Publication(User ownerOfPublication, LocalDateTime publishedOn) {
        this.publishedOn = publishedOn;
        this.ownerOfPublication = ownerOfPublication;
    }

    private void setId(User ownerOfPost) {
        //TO DO
    }

    public void addLike() {

    }

    public void addComment(String text) {

    }

    public boolean isExpired() {
        return false;
    }

    @Override
    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    @Override
    public int getNumberOfComments() {
        return comments.size();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Collection<String> getTags() {
        return tags;
    }

    @Override
    public Collection<String> getMentions() {
        return mentions;
    }
}
