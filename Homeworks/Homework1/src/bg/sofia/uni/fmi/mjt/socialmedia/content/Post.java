package bg.sofia.uni.fmi.mjt.socialmedia.content;

import bg.sofia.uni.fmi.mjt.socialmedia.user.User;

import java.time.LocalDateTime;

public class Post extends Publication {
    public Post(User ownerOfPublication, LocalDateTime publishedOn) {
        super(ownerOfPublication, publishedOn);
    }
}
