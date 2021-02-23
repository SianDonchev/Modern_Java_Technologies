import bg.sofia.uni.fmi.mjt.revolut.Revolut;
import bg.sofia.uni.fmi.mjt.revolut.RevolutAPI;
import bg.sofia.uni.fmi.mjt.revolut.account.Account;
import bg.sofia.uni.fmi.mjt.revolut.account.BGNAccount;
import bg.sofia.uni.fmi.mjt.revolut.account.EURAccount;
import bg.sofia.uni.fmi.mjt.revolut.card.Card;
import bg.sofia.uni.fmi.mjt.revolut.card.PhysicalCard;
import bg.sofia.uni.fmi.mjt.revolut.card.VirtualOneTimeCard;
import bg.sofia.uni.fmi.mjt.revolut.card.VirtualPermanentCard;

import java.time.LocalDate;

public class Program {

    public static void main(String[] args) {
        Card card1 = new PhysicalCard("a1", 123, LocalDate.now().plusDays(1));
        Card card2 = new VirtualOneTimeCard("a2", 123, LocalDate.now().plusDays(1));
        Card card3 = new VirtualPermanentCard("a3", 123, LocalDate.now().plusDays(1));
        Card card4 = new VirtualPermanentCard("a4", 123, LocalDate.now());
        Card card5 = new PhysicalCard("a5", 123, LocalDate.now().plusDays(1));
        Card card6 = new PhysicalCard("a6", 123, LocalDate.now().plusDays(-1));

        Account account1 = new BGNAccount("12311", 100);
        Account account2 = new BGNAccount("12322", 100);
        Account account3 = new BGNAccount("12333", 100);
        Account account4 = new BGNAccount("12344", 100);
        Account account5 = new BGNAccount("12355", 100);
        Account account6 = new BGNAccount("12366", 100);
        Account account7 = new EURAccount("12367", 100);

        Account[] accounts = new Account[7];
        accounts[0] = account1;
        accounts[1] = account2;
        accounts[2] = account3;
        accounts[3] = account4;
        accounts[4] = account5;
        accounts[5] = account6;
        accounts[6] = account7;

        Card[] cards = new Card[6];
        cards[0] = card1;
        cards[1] = card2;
        cards[2] = card3;
        cards[3] = card4;
        cards[4] = card5;
        cards[5] = card6;

        RevolutAPI revolut = new Revolut(accounts, cards);

        System.out.println(revolut.payOnline(card3, 123, 50, "BGN","site.biz"));
        System.out.println(revolut.payOnline(card3, 123, 100, "BGN", "site1.com"));
        System.out.println(revolut.payOnline(card2, 123, 50, "BGN", ".biz.site1.com"));
        System.out.println(revolut.payOnline(card2, 123, 1, "BGN", "site1.com"));
        System.out.println(revolut.pay(card1, 123, 50, "BGN"));

        System.out.println(revolut.transferMoney(account1, account2, 30));
        System.out.println(revolut.getTotalAmount());
        System.out.println(revolut.addMoney(account3,1000));
        System.out.println(revolut.getTotalAmount());

    }
}
