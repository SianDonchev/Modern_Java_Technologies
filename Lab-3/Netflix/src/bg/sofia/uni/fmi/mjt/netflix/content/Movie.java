package bg.sofia.uni.fmi.mjt.netflix.content;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.Genre;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;

public class Movie extends StreamingContent {
    private int duration;
    public Movie(String name, Genre genre, PgRating rating, int duration){
        super(name,genre,rating);
        this.duration = duration;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Movie)){
            return false;
        }
        Movie otherMovie = (Movie) obj;

        return super.equals(otherMovie) && this.duration == otherMovie.duration;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + duration;
    }
}
