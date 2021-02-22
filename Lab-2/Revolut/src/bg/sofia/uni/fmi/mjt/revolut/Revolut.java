package bg.sofia.uni.fmi.mjt.revolut;

import bg.sofia.uni.fmi.mjt.revolut.account.Account;
import bg.sofia.uni.fmi.mjt.revolut.card.Card;

public class Revolut implements RevolutAPI {
    Revolut(Account[] accounts, Card[] cards){}

    @Override
    public boolean pay(Card card, int pin, double amount, String currency) {
        return false;
    }

    @Override
    public boolean payOnline(Card card, int pin, double amount, String currency, String shopURL) {
        return false;
    }

    @Override
    public boolean addMoney(Account account, double amount) {
        return false;
    }

    @Override
    public boolean transferMoney(Account from, Account to, double amount) {
        return false;
    }

    @Override
    public double getTotalAmount() {
        return 0;
    }
}
