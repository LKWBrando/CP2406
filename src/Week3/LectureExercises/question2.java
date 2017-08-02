package Week3.LectureExercises;
import java.util.Scanner;

public class question2 {
    public static void main(String[] args) {
        CashCard card1 = new CashCard("a001", 100.0);
        System.out.println(card1.getCashCardID());
        System.out.println(card1.getCashCardValue());

        if (card1.deduct(101.0)){
            System.out.println("Deduction Successful");}
            else {System.out.println("Not enough money");
        }

        Dice dice1 = new Dice();
        dice1.roll();
        if (dice1.getFaceValue() >= 4){
            card1.topUp((dice1.getFaceValue() * 10.0));
        }
        System.out.println(card1.getCashCardValue());
    }
}
