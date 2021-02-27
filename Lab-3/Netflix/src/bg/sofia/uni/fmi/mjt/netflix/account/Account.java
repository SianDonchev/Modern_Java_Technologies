package bg.sofia.uni.fmi.mjt.netflix.account;

import java.time.LocalDateTime;
import java.time.Period;

public class Account {
    private String username;
    private LocalDateTime bitrhdayDate;

    Account(String username, LocalDateTime birthdayDate) {
        this.username = username;
        this.bitrhdayDate = birthdayDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Account)) {
            return false;
        }
        Account otherAccount = (Account) obj;
        return this.username.equals(otherAccount.username) && this.bitrhdayDate.equals(otherAccount.bitrhdayDate);
    }

    @Override
    public int hashCode() {
        return this.username.hashCode() + this.bitrhdayDate.hashCode();
    }

    public long getYears() {
        return java.time.temporal.ChronoUnit.YEARS.between(this.bitrhdayDate, LocalDateTime.now());
    }
}
