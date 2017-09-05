package AssignmentOne;

import java.util.ArrayList;

public class Player {
    private int playerIndex;
    private ArrayList <Card>playerHand;
    private Boolean playerStatus;

    public Player(int playerIndex){
        this.playerIndex = playerIndex;
        playerHand = new ArrayList<Card>();
        playerStatus = true;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void drawCard(Card nextCard){
        playerHand.add(nextCard);
    }

    public Boolean getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(Boolean playerStatus) {
        this.playerStatus = playerStatus;
    }

    public String toString(){
        String output ="Player: " + playerIndex + " has the following cards:\n";
        for (int x = 0; x< playerHand.size();x++){
            output = output + "\nCard " + (x+1) + ". "+ playerHand.get(x);
        }
        return output;}

}
