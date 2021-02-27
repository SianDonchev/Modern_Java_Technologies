package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.content.StreamingContent;
import bg.sofia.uni.fmi.mjt.netflix.content.enums.PgRating;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentNotFoundException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.UserNotFoundException;

import java.time.LocalDateTime;
import java.time.Period;

public class Netflix implements StreamingService {
    private Account[] accounts;
    private Streamable[] streamableContent;

    public Netflix(Account[] accounts, Streamable[] streamableContent){
        this.accounts = accounts;
        this.streamableContent = streamableContent;
    }
    
    @Override
    public void watch(Account user, String videoContentName) throws ContentUnavailableException {
        if(!isUserRegistered(user)){
            throw new UserNotFoundException();
        }
        Streamable toWatch = findByName((videoContentName));
        if(toWatch == null){
            throw new ContentNotFoundException();
        }
        if(!canWatch(toWatch,user)){
            throw new ContentUnavailableException();
        }
    }

    @Override
    public Streamable findByName(String videoContentName) {
        for(Streamable streamable : streamableContent){
            if(streamable.getTitle().equals(videoContentName)){
                return streamable;
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
        return 0;
    }

    private boolean isUserRegistered(Account user){
        for(Account account : accounts){
            if(account.equals(user)){
                return true;
            }
        }
        return false;
    }

    public boolean canWatch(Streamable streamable,Account user) {
        if (streamable.getRating().equals(PgRating.NC17) && user.getYears() < 18) {
            return false;
        }
        if (streamable.getRating().equals(PgRating.PG13) && user.getYears() < 14) {
            return false;
        }
        return true;
    }
}
