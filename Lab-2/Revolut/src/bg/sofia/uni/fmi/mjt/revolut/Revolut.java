package bg.sofia.uni.fmi.mjt.revolut;

import bg.sofia.uni.fmi.mjt.revolut.account.Account;
import bg.sofia.uni.fmi.mjt.revolut.card.Card;

import java.time.LocalDate;

public class Revolut implements RevolutAPI {
    private Account[] accounts;
    private Card[] cards;

    public static final double EUR_TO_BGN_EXCHANGE_RATE = 1.95583;
    public static final String BLOCKED_TOP_LEVEL_DOMAIN = "biz";


    Revolut(Account[] accounts, Card[] cards) {
        this.accounts = accounts;
        this.cards = cards;
    }

    @Override
    public boolean pay(Card card, int pin, double amount, String currency) {
        if (card.getType() == "PHYSICAL" && isAccepted(card, pin, amount, currency)) {
            int indexForAccountToWithdraw = accountWithTheSufficientAmount(amount);
            Account accountToWithdrawFrom = accounts[indexForAccountToWithdraw];
            accountToWithdrawFrom.withdraw(amount);
            if (card.getType().equals("VIRTUALONETIME")) {
                card.block();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean payOnline(Card card, int pin, double amount, String currency, String shopURL) {
        if (!isBlockedURL(shopURL) && isAccepted(card, pin, amount, currency)) {
            int indexForAccountToWithdraw = accountWithTheSufficientAmount(amount);
            Account accountToWithdrawFrom = accounts[indexForAccountToWithdraw];
            accountToWithdrawFrom.withdraw(amount);
            if (card.getType().equals("VIRTUALONETIME")) {
                card.block();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean addMoney(Account account, double amount) {
        if (isValidAccount(account)) {
            account.addMoney(amount);
            return true;
        }
        return false;
    }

    @Override
    public boolean transferMoney(Account from, Account to, double amount) {
        if (isValidAccount(from) && isValidAccount(to) && !from.equals(to) && from.hasEnoughMoney(amount)) {

            if (from.getCurrency().equals(to.getCurrency())) {
                from.withdraw(amount);
                to.addMoney(amount);
                return true;
            }

            double convertedAmmount = from.getCurrency().equals("BGN") ? amount / EUR_TO_BGN_EXCHANGE_RATE : amount * EUR_TO_BGN_EXCHANGE_RATE;
            from.withdraw(amount);
            to.addMoney(convertedAmmount);
        }
        return false;
    }

    @Override
    public double getTotalAmount() {
        int totalAmount = 0;
        for (Account account : accounts) {
            if(account.getCurrency().equals("BGN")) {
                totalAmount += account.getAmount();
            }
            else{
                totalAmount += account.getAmount() * EUR_TO_BGN_EXCHANGE_RATE;
            }
        }
        return totalAmount;
    }

    private boolean isBlockedURL(String URL) {
        int indexOfBlockedDomain = URL.lastIndexOf(BLOCKED_TOP_LEVEL_DOMAIN);
        if (indexOfBlockedDomain == URL.length() - 4) {
            return true;
        }
        return false;
    }

    private boolean isValidAccount(Account account) {
        for (Account a : accounts) {
            if (a.equals(account)) {
                return true;
            }
        }
        return false;
    }

    private int accountWithTheSufficientAmount(double amount) {
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i].getAmount() > amount) {
                return i;
            }
        }
        return -1;
    }

    private boolean isAvailavle(Card card) {
        for (Card c : cards) {
            if (c.equals(card)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValid(Card card) {
        return card.getExpirationDate().isAfter(LocalDate.now());
    }

    private boolean isAccepted(Card card, int pin, double amount, String currency) {
        return isAvailavle(card) && isValid(card) && !card.isBlocked() && card.checkPin(pin);
    }
}
