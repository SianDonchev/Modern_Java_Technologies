package bg.sofia.uni.fmi.mjt.socialmedia.content;

import bg.sofia.uni.fmi.mjt.socialmedia.user.User;

import java.time.LocalDateTime;

public class Story extends Publication {
    public Story(User ownerOfPublication, LocalDateTime publishedOn) {
        super(ownerOfPublication, publishedOn);
    }
}
