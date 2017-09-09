/*
The Player Class is used to create objects with the attributes of the players involved in the game.
Variables involved are:
1. playerIndex, used to name and identify the player object according to an integer value.
2. playerHand, an arraylist used to store Card objects as dealt to the player object in the game.
3. playerGameStatus, a Boolean variable used to determine if the player object has finished the game or not, with the default value of TRUE that indicates the player is still playing the game.
4. playerTurnStatus, a Boolean variable used to determine if the player object has passed his/her turn or not, with the default value of TRUE that indicates the player still has his/her turn.
 */

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
        playerTurnStatus = true;
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

    public void setPlayerTurnStatus(Boolean playerTurnStatus){this.playerTurnStatus = playerTurnStatus;}

    public String toString(){
        String output ="Player: " + playerIndex + " has the following cards:\n";
        for (int x = 0; x< playerHand.size();x++){
            output = output + "\nCard " + (x+1) + ". "+ playerHand.get(x);
        }
        return output;}

}
