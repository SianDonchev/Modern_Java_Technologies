package bg.sofia.uni.fmi.mjt.revolut.account;

public class BGNAccount extends Account {
    public BGNAccount(String IBAN) {
        super(IBAN);
    }

    @Override
    public String getCurrency() {
        return "BGN";
    }
}
