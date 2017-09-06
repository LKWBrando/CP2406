package AssignmentOne;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) throws IOException {
        //Reading from the card.txt file
        ArrayList<Card> cardDeck = new ArrayList<Card>();
        Scanner inputFile = new Scanner(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\src\\AssignmentOne\\card.txt"));
        //Creating a Card object based on each line in the txt file
        while (inputFile.hasNextLine()) {
            String[] fileLine = inputFile.nextLine().split(",");
            try {
                //Parsing string values in the txt file to double values as defined in the Card object
                double convMinHD = Double.parseDouble(fileLine[1]);
                double convMinSG = Double.parseDouble(fileLine[2]);
                Card newCard = new Card(fileLine[0], convMinHD, convMinSG, fileLine[3], fileLine[4], fileLine[5]);
                cardDeck.add(newCard);
            } catch (NumberFormatException error) {
            }
        }
        inputFile.close();

        //Asking for user input on the number of players
        int playerCount = 0;
        Scanner gameStart = new Scanner(System.in);
        System.out.println("Please enter the number of players (Min 3, Max 5)");
        playerCount = gameStart.nextInt();
        while (playerCount < 3 || playerCount > 5) {
            System.out.println("You have entered an invalid response, please enter a number from 3 to 5");
            playerCount = gameStart.nextInt();
        }
        //Creating an arraylist to store the Player objects
        ArrayList<Player> playerList = new ArrayList<Player>();
        int newPlayerCount = 1;
        for (int x = 1; x <= playerCount; x++) {
            Player newPlayer = new Player(newPlayerCount);
            newPlayerCount += 1;
            playerList.add(newPlayer);
        }
        //Initialising the player hand, dealing eight cards randomly to each of the player
        Random randInt = new Random();
        int totalCardCount = 54;
        int dealCard = 0;
        for (int x = 0; x < playerCount; x++) {
            for (int y = 0; y < 8; y++) {
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(x).drawCard(cardDeck.get(dealCard));
                cardDeck.remove(dealCard);
                totalCardCount -= 1;
            }
        }

        System.out.println("The amount of cards left in the deck is: " + totalCardCount + "\nThe game is starting! ");
        System.out.println("*******************************************************************************************");

        int playerSelection; //Variable based on player input for card selection from their hand
        ArrayList<String> finishedPlayerList = new ArrayList<String>(); //Arraylist to store finished players according to their ranking.
        int finishedPlayers = 0; //Variable used to count the number of finished players.
        int playCategory; // Variable used to store user selection on which Trump category is to be played.
        int lastPlayerAction = 0; //to be replaced to corrent gameplay mechanics.
        int playerTurnCount = 0;

        String currentTrump = null; //Variable based on current Trump category in play.
        String currentTrumpName = null; //Variable based on name of the current card in play.
        double currentTrumpValue = 0; //Variable based on the current value of the Trump category in play.
        String displayCurrentTrumpValue = null; //Variable based on the String values of some of the Trump categories in play.
        Boolean runGame = true; //Boolean Variable used to create an endless loop

        while (runGame) {
            for (int x = 0; x < playerCount; x++) {
                if (playerList.get(x).getPlayerGameStatus()) {
                    System.out.println("\nPlayer " + (x + 1) + "'s Turn! \n");
                    //If there are still more than one player who is playing the card game
                    if (!playerList.get(x).getPlayerTurnStatus()) {
                        System.out.println("You are unable to get a turn! Please wait until all but one player has passed.");
                    } else {
                        System.out.println(playerList.get(x));
                        //This for the first player to start the game by playing the first card
                        if (currentTrumpName == null) {
                            System.out.println("\nYou get to play the first card!");
                            Scanner firstPlayCard = new Scanner(System.in);
                            System.out.println("Play a card by selecting the number");
                            playerSelection = firstPlayCard.nextInt();
                            Scanner userInputPlayCat = new Scanner(System.in);
                            System.out.println("You have selected: " + playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName() + "\nChoose a playing category: [1] Hardness, [2] SG, [3] Cleavage, [4] CA, [5] EV");
                            playCategory = userInputPlayCat.nextInt();

                            currentTrumpName = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName();
                            currentTrump = changeTrump(playCategory);
                            currentTrumpValue = changeTrumpValue(playCategory, playerList.get(x).getPlayerHand().get(playerSelection - 1));
                            displayCurrentTrumpValue = changeTrumpStringValue(playCategory, playerList.get(x).getPlayerHand().get(playerSelection - 1));

                            System.out.println("\nPlayer " + (x + 1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection - 1));
                            playerList.get(x).getPlayerHand().remove(playerSelection - 1);
                        }
                        //This is for subsequent turns where the player is able to play any cards from the hand.
                        else {
                            if (playerTurnCount == playerCount - 1) {
                                System.out.println("\nNo other player has managed to play a card! You are free to play any cards! \nPlay a card by selecting the number.");
                                for (Player aPlayer : playerList) {
                                    aPlayer.setPlayerTurnStatus(true);
                                }
                                playerTurnCount = 0;

                                Scanner passedTurns = new Scanner(System.in);
                                playerSelection = passedTurns.nextInt();
                                Scanner userInputPlayCat = new Scanner(System.in);
                                System.out.println("You have selected: " + playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName());
                                System.out.println("Choose a playing category: [1] Hardness, [2] SG, [3] Cleavage, [4] CA, [5] EV");
                                playCategory = userInputPlayCat.nextInt();

                                //Setting the following variables to reflect the properties of the card that the player has played.
                                currentTrumpName = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName();
                                currentTrump = changeTrump(playCategory);
                                currentTrumpValue = changeTrumpValue(playCategory, playerList.get(x).getPlayerHand().get(playerSelection - 1));
                                displayCurrentTrumpValue = changeTrumpStringValue(playCategory, playerList.get(x).getPlayerHand().get(playerSelection - 1));
                                System.out.println("\nPlayer " + (x + 1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection - 1));
                                playerList.get(x).getPlayerHand().remove(playerSelection - 1);
                                if (playerList.get(x).getPlayerHand().size() == 0) {
                                    System.out.println("Player " + (x + 1) + "has finished the game!");
                                    playerList.get(x).setPlayerGameStatus(false);
                                    finishedPlayerList.add("Player" + (x + 1));
                                    finishedPlayers += 1;
                                    currentTrumpName = null;
                                    if (finishedPlayers == playerCount - 1) {
                                        runGame = false;
                                    }
                                }
                            }
                            //Proceeding with the game, always starting with displaying the current card in play.
                            else {
                                if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                                    System.out.println("\nCurrent card in play is: " + currentTrumpName + " . The chosen trump category is:" + currentTrump + " with a current value of: " + displayCurrentTrumpValue);
                                } else {
                                    System.out.println("\nCurrent card in play is: " + currentTrumpName + " . The chosen trump category is:" + currentTrump + " with a current value of: " + currentTrumpValue);
                                }

                                Scanner userInputGameMenu = new Scanner(System.in);
                                System.out.println("\nPlay a card by selecting the number or press 0 to Pass and draw a card!");
                                playerSelection = userInputGameMenu.nextInt();
                                // Variable decidingTrumpValue is used to determine if the value of the Trump of the card is higher or lower than the one in play
                                double decidingTrumpValue = 0;
                                if (playerSelection > 0 && playerSelection <= playerList.get(x).getPlayerHand().size()) {
                                    decidingTrumpValue = calTrumpValue(currentTrump, playerList.get(x).getPlayerHand().get(playerSelection - 1));

                                    while (decidingTrumpValue <= currentTrumpValue) {
                                        System.out.println("The card you have played has a lower value in the selected category! Please select another card.");
                                        Scanner userNextInputGameMenu = new Scanner(System.in);
                                        playerSelection = userNextInputGameMenu.nextInt();
                                        decidingTrumpValue = calTrumpValue(currentTrump, playerList.get(x).getPlayerHand().get(playerSelection - 1));
                                    }

                                    currentTrumpName = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName();
                                    currentTrumpValue = decidingTrumpValue;
                                    System.out.println("Player " + (x + 1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection - 1));
                                    playerList.get(x).getPlayerHand().remove(playerSelection - 1);
                                    if (playerList.get(x).getPlayerHand().isEmpty()) {
                                        System.out.println("Player " + (x + 1) + " has finished the game! The game continues!");
                                        playerList.get(x).setPlayerGameStatus(false);
                                        finishedPlayerList.add("Player" + (x + 1));
                                        finishedPlayers += 1;
                                        currentTrumpName = null;
                                        if (finishedPlayers == playerCount - 1) {
                                            runGame = false;
                                        }
                                    }
                                }
                                if (playerSelection == 0) {
                                    if (totalCardCount != 0) {
                                        dealCard = randInt.nextInt(totalCardCount);
                                        playerList.get(x).drawCard(cardDeck.get(dealCard));
                                        cardDeck.remove(dealCard);
                                        totalCardCount -= 1;
                                        playerTurnCount += 1;
                                        playerList.get(x).setPlayerTurnStatus(false);

                                            System.out.println("Player " + (x + 1) + " has passed and drew a card! \nThe amount of cards left in the deck is: " + totalCardCount + "\nPlease wait for your next turn!");
                                        } else {
                                            playerTurnCount += 1;
                                            playerList.get(x).setPlayerTurnStatus(false);
                                            System.out.println("There are no more cards left in the deck! Please wait for your next turn.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.println("The game has finished! \nThe overall winner is: " + finishedPlayerList.get(0) + "\nHere are the runner ups:");
        }

    //Method to set the trump category based on user selection.
    public static String changeTrump(int playCategory){
        String currentTrump = null;
        switch (playCategory) {
            case 1:
                currentTrump = "Hardness";
                break;
            case 2:
                currentTrump = "Specific Gravity";
                break;
            case 3:
                currentTrump = "Cleavage";
                break;
            case 4:
                currentTrump = "Crystal Abundance";
                break;
            case 5:
                currentTrump = "Economic Value";
                break;
        }
        return currentTrump;
    }
    //Method to change value of the trump category based on the card the user has played.
    public static double changeTrumpValue(int playCategory, Card playedCard){
        double currentTrumpValue = 0.0;
        switch (playCategory) {
            case 1:
                currentTrumpValue = playedCard.getMineralHD();
                break;
            case 2:
                currentTrumpValue = playedCard.getMineralSG();
                break;
            case 3:
                currentTrumpValue = playedCard.getMineralCleavageValue(playedCard.getMineralCleavage());
                break;
            case 4:
                currentTrumpValue = playedCard.getMineralCaValue(playedCard.getMineralCA());
                break;
            case 5:
                currentTrumpValue = playedCard.getEvValue(playedCard.getMineralEcoValue());
                break;}
        return currentTrumpValue;
    }

    public static String changeTrumpStringValue(int playCategory, Card playedCard){
        String displayCurrentTrumpValue = null;
        switch (playCategory) {
            case 1:
                displayCurrentTrumpValue = null;
                break;
            case 2:
                displayCurrentTrumpValue = null;
                break;
            case 3:
                displayCurrentTrumpValue = playedCard.getMineralCleavage();
                break;
            case 4:
                displayCurrentTrumpValue = playedCard.getMineralCA();
                break;
            case 5:
                displayCurrentTrumpValue = playedCard.getMineralEcoValue();
                break;}
        return displayCurrentTrumpValue;
    }

    public static double calTrumpValue(String currentTrump, Card playedCard){
        double decidingTrumpValue = 0.0;
        switch (currentTrump) {
            case "Hardness":
                decidingTrumpValue = playedCard.getMineralHD();
                break;
            case "Specific Gravity":
                decidingTrumpValue = playedCard.getMineralSG();
                break;
            case "Cleavage":
                decidingTrumpValue = playedCard.getMineralCleavageValue(playedCard.getMineralCleavage());
                break;
            case "Crystal Abuandance":
                decidingTrumpValue = playedCard.getMineralCaValue(playedCard.getMineralCA());
                break;
            case "Economic Value":
                decidingTrumpValue = playedCard.getEvValue(playedCard.getMineralEcoValue());
        }
        return decidingTrumpValue;
    }
}
