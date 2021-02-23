package bg.sofia.uni.fmi.mjt.revolut.account;

public class EURAccount extends Account {
    public EURAccount(String IBAN) {
        super(IBAN);
    }

    @Override
    public String getCurrency() {
        return null;
    }
}
