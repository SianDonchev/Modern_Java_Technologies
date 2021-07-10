package bg.sofia.uni.fmi.mjt.socialmedia.content;

import java.util.Comparator;

public class ContentByLikesAndCommentsComparator implements Comparator<Content> {

    @Override
    public int compare(Content content1, Content content2) {
        int popularity1 = content1.getNumberOfLikes() + content1.getNumberOfComments();
        int popularity2 = content2.getNumberOfLikes() + content2.getNumberOfComments();

        if(popularity1 > popularity2) {
            return 1;
        }

        return popularity1 == popularity2 ? 0 : -1;
    }
}
