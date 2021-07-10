package bg.sofia.uni.fmi.mjt.socialmedia.user;

import java.time.LocalDateTime;

public interface ActiveUser {
    public String publishPost(LocalDateTime publishedOn, String description);

    public String publishStory(LocalDateTime publishedOn, String description);

    public void comment(String text, String id);

    public void like(String Id);
}
