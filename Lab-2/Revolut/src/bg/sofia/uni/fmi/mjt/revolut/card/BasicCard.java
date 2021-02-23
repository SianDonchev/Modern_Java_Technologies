package bg.sofia.uni.fmi.mjt.revolut.card;

import java.time.LocalDate;
import java.util.Objects;

public abstract class BasicCard implements Card{
    private String number;
    private int pin;
    private LocalDate expirationDate;
    private boolean blocked;
    protected int numberOfUncorrectPins;

    BasicCard(String number, int pin, LocalDate expirationDate){
        this.number = number;
        this.pin = pin;
        this.expirationDate = expirationDate;
    }
    public LocalDate getExpirationDate(){
        return expirationDate;
    }

    public boolean checkPin(int pin){
        if(this.pin == pin){
            annulNumberOfUncorrectPins();
            return true;
        }
        increaseNumberOfUncorrectPins();
        if(enoughUncorrectPinsForBlock()) {
            block();
        }
        return false;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public void block(){
        blocked = true;
    }

    @Override
    public boolean equals(Object obj) {
        return this.number.equals(((BasicCard) obj).number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.number);
    }

    protected boolean enoughUncorrectPinsForBlock(){
        return numberOfUncorrectPins == 3;
    }

    private void increaseNumberOfUncorrectPins(){
        ++numberOfUncorrectPins;
    }

    private void annulNumberOfUncorrectPins(){
        numberOfUncorrectPins = 0;
    }
}
