package AssignmentOne;

import java.util.ArrayList;

public class Player {
    private int playerIndex;
    private ArrayList playerHand;

    public Player(int playerIndex){
        this.playerIndex = playerIndex;
        this.playerHand = new ArrayList();
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public ArrayList getPlayerHand() {
        return playerHand;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public void setPlayerHand(ArrayList playerHand) {
        this.playerHand = playerHand;
    }

    public void addCard(Card nextCard){
        playerHand.add(nextCard);
    }

    /*public void addCard(Card newCard){
        playerHand.add();
    }*/

    public String toString(){
        return "\n Player: " + playerIndex + " has the following cards: " + playerHand;
    }
}
