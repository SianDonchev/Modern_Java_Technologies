package bg.sofia.uni.fmi.mjt.socialmedia.content;

public class Comment extends Operation {
    String content;

    public Comment(String id, String content){
        this.idOfPost = id;
        this.content = content;
    }

    @Override
    public String getContent() {
        return content;
    }
}
