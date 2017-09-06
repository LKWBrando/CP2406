package AssignmentOne;

import java.util.ArrayList;

public class Player {
    private int playerIndex;
    private ArrayList <Card>playerHand;
    private Boolean playerGameStatus;
    private Boolean playerTurnStatus;

    public Player(int playerIndex){
        this.playerIndex = playerIndex;
        playerHand = new ArrayList<Card>();
        playerGameStatus = true;
        playerTurnStatus = false;
    }

    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    public void drawCard(Card nextCard){
        playerHand.add(nextCard);
    }

    public Boolean getPlayerGameStatus() {
        return playerGameStatus;
    }

    public Boolean getPlayerTurnStatus(){return playerTurnStatus; }

    public void setPlayerGameStatus(Boolean playerGameStatus) {
        this.playerGameStatus = playerGameStatus;
    }

    public String toString(){
        String output ="Player: " + playerIndex + " has the following cards:\n";
        for (int x = 0; x< playerHand.size();x++){
            output = output + "\nCard " + (x+1) + ". "+ playerHand.get(x);
        }
        return output;}

}
