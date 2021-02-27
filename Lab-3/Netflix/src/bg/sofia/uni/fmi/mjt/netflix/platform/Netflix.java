package bg.sofia.uni.fmi.mjt.netflix.platform;

import bg.sofia.uni.fmi.mjt.netflix.account.Account;
import bg.sofia.uni.fmi.mjt.netflix.content.Streamable;
import bg.sofia.uni.fmi.mjt.netflix.exceptions.ContentUnavailableException;

public class Netflix implements StreamingService {
    private Account[] accounts;
    private Streamable[] streamableContent;

    public Netflix(Account[] accounts, Streamable[] streamableContent){
        this.accounts = accounts;
        this.streamableContent = streamableContent;
    }

    @Override
    public void watch(Account user, String videoContentName) throws ContentUnavailableException {

    }

    @Override
    public Streamable findByName(String videoContentName) {
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
}
