package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.PairStreamableViews;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.content.StreamingContent;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentNotFoundException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.stream.Stream;

public class Netflix implements StreamingService {
    private Account[] accounts;
    private PairStreamableViews[] streamableContentAndViews;

    public Netflix(Account[] accounts, Streamable[] streamableContent) {
        this.accounts = accounts;
        initStreamableContentAndViews(streamableContent);
    }

    @Override
    public void watch(Account user, String videoContentName) throws ContentUnavailableException {
        if (!isUserRegistered(user)) {
            throw new UserNotFoundException();
        }
        Streamable toWatch = findByName((videoContentName));
        if (toWatch == null) {
            throw new ContentNotFoundException();
        }
        if (!canWatch(toWatch, user)) {
            throw new ContentUnavailableException();
        }
        increaseViews(toWatch);
    }

    @Override
    public Streamable findByName(String videoContentName) {
        for (PairStreamableViews pairStreamableViews : streamableContentAndViews) {
            if (pairStreamableViews.getStreamable().getTitle().equals(videoContentName)) {
                return pairStreamableViews.getStreamable();
            }
        }
        return null;
    }

    @Override
    public Streamable mostViewed() {
        return null;
    }

    @Override
    public int totalWatchedTimeByUsers() {
        int totalWatchedTime = 0;
        for (PairStreamableViews pairStreamableViews : streamableContentAndViews) {
            int totalViews = pairStreamableViews.getViews();
            int duration = pairStreamableViews.getStreamable().getDuration();
            totalWatchedTime = totalViews * duration;
        }
        return totalWatchedTime;
    }

    private void initStreamableContentAndViews(Streamable[] streamableContent) {
        int length = streamableContentAndViews.length;
        streamableContentAndViews = new PairStreamableViews[length];
        for (int i = 0; i < length; ++i) {
            streamableContentAndViews[i] = new PairStreamableViews(streamableContent[i]);
        }
    }

    private void increaseViews(Streamable streamable) {
        for (PairStreamableViews pairStreamableViews : streamableContentAndViews) {
            if (pairStreamableViews.getStreamable().equals(streamable)) {
                pairStreamableViews.increaseViews();
            }
        }
    }

    private boolean isUserRegistered(Account user) {
        for (Account account : accounts) {
            if (account.equals(user)) {
                return true;
            }
        }
        return false;
    }

    public boolean canWatch(Streamable streamable, Account user) {
        if (streamable.getRating().equals(PgRating.NC17) && user.getYears() < 18) {
            return false;
        }
        if (streamable.getRating().equals(PgRating.PG13) && user.getYears() < 14) {
            return false;
        }
        return true;
    }
}
