package AssignmentOne;

/*
The Card class is created to be the parent Class of MineralCard and SuperTrumpCard.
Both the MineralCard and SuperTrumpCard classes will inherit the variable mineralName, which is used to store the Name of the card.
 */
public class Card {
    /*Variable involved is:
    1. mineralName, used to store the name of the card object.*/
    private String mineralName;

    //Constructor for the Card class
    public Card(String mineralName){
         this.mineralName = mineralName;
     }

    //Getter for the variable mineralName
    public String getMineralName(){
        return mineralName;
    }

    //Overwritten toString() to display custom string output
    public String toString(){
         return String.format("Name: %-18s |",mineralName);
    }
}
