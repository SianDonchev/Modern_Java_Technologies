package bg.sofia.uni.fmi.mjt.netflix.content;

public class PairStreamableViews {
    private Streamable streamableContent;
    private int views;

    public PairStreamableViews(Streamable streamableContent){
        this.streamableContent = streamableContent;
    }

    public Streamable getStreamable(){
        return streamableContent;
    }

    public int getViews(){
        return views;
    }

    public void increaseViews(){
        ++views;
    }
}
