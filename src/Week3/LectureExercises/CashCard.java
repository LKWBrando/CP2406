package Week3.LectureExercises;
import java.util.Scanner;

public class CashCard {
    private String CashCardID;
    private Double CashCardValue;

    public CashCard(String CashCardID, Double CashCardValue){
        //this. is used to resolve ambiguity
        this.CashCardID = CashCardID;
        this.CashCardValue = CashCardValue;
    }

    //Multiple constructor methods for different scenarios, the one below creates a card with 'empty' values
    public CashCard(){
        //CashCardID = "NA";
        //CashCardValue = 0.0;
        this("NA", 0.0);
    }

    public String getCashCardID() {
        return CashCardID;
    }

    public void setCashCardID(String NewCardID){
        CashCardID = NewCardID;
    }

    public Double getCashCardValue() {
        return CashCardValue;
    }

    public void topUp(Double inputAmount){
        CashCardValue = CashCardValue + inputAmount;
    }

    public Boolean deduct(Double inputAmount){
        if (CashCardValue<inputAmount){
        return false;}
        else {CashCardValue = CashCardValue - inputAmount;
            return true;}
    }
}

