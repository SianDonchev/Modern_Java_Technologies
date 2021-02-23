package bg.sofia.uni.fmi.mjt.revolut.card;

import java.time.LocalDate;

public abstract class BasicCard implements Card{
    private String number;
    private int pin;
    private LocalDate expirationDate;
    private boolean blocked;

    BasicCard(String number, int pin, LocalDate expirationDate){
        this.number = number;
        this.pin = pin;
        this.expirationDate = expirationDate;
    }
    public LocalDate getExpirationDate(){
        return expirationDate;
    }

    public boolean checkPin(int pin){
        return this.pin == pin;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public void block(){
        blocked = true;
    }
}
