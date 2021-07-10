package bg.sofia.uni.fmi.mjt.socialmedia.content;

public abstract class Operation implements Activity {
    String idOfPost;

    @Override
    public String getContent() {
        return null;
    }

    @Override
    public String getPostForActivity() {
        return idOfPost;
    }
}
