package AssignmentOne;

import java.util.ArrayList;

public class Player {
    private int playerIndex;
    private ArrayList playerHand;
    private Boolean playerStatus;

    public Player(int playerIndex){
        this.playerIndex = playerIndex;
        this.playerHand = new ArrayList();
        this.playerStatus = true;
    }

    public int getPlayerIndex() {
        return playerIndex;
    }

    public void setPlayerIndex(int playerIndex) {
        this.playerIndex = playerIndex;
    }

    public ArrayList getPlayerHand() {
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
        String output ="Player: " + playerIndex + " has the following cards:";
        for (int x = 0; x< playerHand.size();x++){
            output = output + "\nCard " + (x+1) + ". "+ playerHand.get(x);
        }
        return output;}

}
