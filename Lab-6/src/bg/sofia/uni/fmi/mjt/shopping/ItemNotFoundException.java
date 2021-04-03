package bg.sofia.uni.fmi.mjt.shopping;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(final String message) {
        super(message);
    }
}