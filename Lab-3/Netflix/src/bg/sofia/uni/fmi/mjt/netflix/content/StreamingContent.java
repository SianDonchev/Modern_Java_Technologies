package bg.sofia.uni.fmi.mjt.netflix.content;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public abstract class StreamingContent implements Streamable {
    private String title;
    private PgRating rating;
    private Genre genre;

    public StreamingContent(String name, Genre genre, PgRating rating) {
        this.title = name;
        this.genre = genre;
        this.rating = rating;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public PgRating getRating() {
        return rating;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreamingContent)) {
            return false;
        }
        StreamingContent otherStreamingContent = (StreamingContent) obj;

        return this.title.equals(otherStreamingContent.title) && this.genre.equals(otherStreamingContent.genre)
                && this.rating.equals(otherStreamingContent.rating);
    }

    @Override
    public int hashCode() {
        return this.rating.hashCode() + this.title.hashCode() + this.genre.hashCode();
    }
}