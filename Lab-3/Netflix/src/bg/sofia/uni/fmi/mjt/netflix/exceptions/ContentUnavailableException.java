package bg.sofia.uni.fmi.mjt.netflix.exceptions;

public class ContentUnavailableException extends RuntimeException {
    public ContentUnavailableException() {
        super("Content is unavailable");
    }
}
