import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class Remembrall {
    public static boolean isPhoneNumberForgettable(String phoneNumber){
        int[] countDigits = new int[10];
        char[] phoneNumberArray = phoneNumber.toCharArray();

        if(phoneNumber.isEmpty()){
            return false;
        }
        
        for(Character c : phoneNumberArray){
            if(isDigit(c)){
                ++countDigits[c - 48];
            }
            else if(isLetter(c)){
                return true;
            }
        }

        for(int counter : countDigits){
            if(counter > 1){
                return false;
            }
        }

        return true;
    }
    public static void main(String[] args) {
        System.out.println(isPhoneNumberForgettable(""));
        System.out.println(isPhoneNumberForgettable("498-123-123"));
        System.out.println(isPhoneNumberForgettable("0894 123 567"));
        System.out.println(isPhoneNumberForgettable("(888)-FLOWERS"));
        System.out.println(isPhoneNumberForgettable("(444)-greens"));
    }
}
