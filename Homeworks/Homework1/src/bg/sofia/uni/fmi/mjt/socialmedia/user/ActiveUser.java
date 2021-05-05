package bg.sofia.uni.fmi.mjt.socialmedia.user;

import java.time.LocalDateTime;

public interface ActiveUser {
    public void publishPost(LocalDateTime publishedOn, String description);

    public void publishStory(LocalDateTime publishedOn, String description);

    public void comment(String text, String id);
}
