package AssignmentOne;
import java.util.ArrayList;

//The Player Class is used to create objects with the attributes of the players involved in the game.
public class Player {

    /*Variables involved are:
    1. playerIndex, used to name and identify the player object according to an integer value.
    2. playerHand, an arraylist used to store Card objects as dealt to the player object in the game.
    3. playerGameStatus, a Boolean variable used to determine if the player object has finished the game or not, with the default value of TRUE that indicates the player is still playing the game.
    4. playerTurnStatus, a Boolean variable used to determine if the player object has passed his/her turn or not, with the default value of TRUE that indicates the player still has his/her turn.*/
    private int playerIndex;
    private ArrayList <Card>playerHand;
    private Boolean playerGameStatus;
    private Boolean playerTurnStatus;

    //Constructor for the Player Class;
    public Player(int playerIndex){
        this.playerIndex = playerIndex;
        playerHand = new ArrayList<Card>();
        playerGameStatus = true;
        playerTurnStatus = true;
    }

    //Getter for the Arraylist playerHand
    public ArrayList<Card> getPlayerHand() {
        return playerHand;
    }

    //Getter for the Boolean variable playerGameStatus
    public Boolean getPlayerGameStatus() {
        return playerGameStatus;
    }

    //Setter for the Boolean variable playerGameStatus
    public void setPlayerGameStatus(Boolean playerGameStatus) {
        this.playerGameStatus = playerGameStatus;
    }

    //Getter for the Boolean variable playerTurnStatus
    public Boolean getPlayerTurnStatus(){
        return playerTurnStatus;
    }

    //Setter for the Boolean variable playerTurnStatus
    public void setPlayerTurnStatus(Boolean playerTurnStatus){
        this.playerTurnStatus = playerTurnStatus;
    }

    //method to add a Card object into the Arraylist playerHand
    public void drawCard(Card nextCard){
        playerHand.add(nextCard);
    }

    //Overwritten toString() to display customer output that displays the player's index and objects within the player hand Arraylist.
    public String toString(){
        String output ="Player: " + playerIndex + " has the following cards:\n";
        //for object in playerHand, print object
        for (int x = 0; x< playerHand.size();x++){
            output = output + "\nCard " + (x+1) + ". "+ playerHand.get(x);
        }
        return output;}

}
