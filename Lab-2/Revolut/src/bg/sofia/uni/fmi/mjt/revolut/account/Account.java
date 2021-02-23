package bg.sofia.uni.fmi.mjt.revolut.account;

import bg.sofia.uni.fmi.mjt.revolut.card.BasicCard;

import java.util.Objects;

public abstract class Account {

    private double amount;
    private String IBAN;

    public Account(String IBAN) {
        this(IBAN, 0);
    }

    public Account(String IBAN, double amount) {
        this.IBAN = IBAN;
        this.amount = amount;
    }

    public abstract String getCurrency();

    public double getAmount() {
        return amount;
    }

    public void withdraw(double amount){
        this.amount -= amount;
    }

    public void addMoney(double amount){
        this.amount += amount;
    }

    public boolean hasEnoughMoney(double amount){
        return this.amount > amount;
    }

    @Override
    public boolean equals(Object obj) {
        return this.IBAN.equals(((Account) obj).IBAN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.IBAN);
    }

    // complete the implementation

}