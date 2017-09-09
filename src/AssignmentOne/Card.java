/*
The Card class is created to be the parent Class of MineralCard and SuperTrumpCard.
Both the MineralCard and SuperTrumpCard classes will inherit the variable mineralName, which is used to store the Name of the card.
Variable involved is:
1. mineralName, used to store the name of the card object.
 */

package AssignmentOne;

public class Card {
    private String mineralName;

    public Card(String mineralName){
         this.mineralName = mineralName;
     }

    public String getMineralName(){
        return mineralName;
    }

    public String toString(){
         return String.format("Name: %-15s |",mineralName);
    }
}
