package bg.sofia.uni.fmi.mjt.netflix.content;

public class Episode {
    private String name;
    private int duration;

    Episode(String name, int duration){
        this.name = name;
        this.duration = duration;
    }

    public int getDuration(){
        return duration;
    }

    public String getName(){
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(!(obj instanceof Episode)){
            return false;
        }

        Episode otherEpisode = (Episode) obj;
        return this.name.equals(otherEpisode.name) && this.duration == otherEpisode.duration;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + duration;
    }
}
