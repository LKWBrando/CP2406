package AssignmentOne;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {

    //The start of the main method
    public static void main(String[] args) throws IOException {

        //Reading from the card.txt file
        ArrayList<Card> cardDeck = new ArrayList<Card>();
        FileReader readFile = new FileReader("C:\\Users\\user\\IdeaProjects\\CP2406\\src\\AssignmentOne\\card.txt");
        BufferedReader newFileLine = new BufferedReader(readFile);
        String nextLine;
        while ((nextLine = newFileLine.readLine()) != null){
            String[] splitData = nextLine.split(",");
            try{
                MineralCard newCard = new MineralCard(splitData[0], Double.parseDouble(splitData[1]), Double.parseDouble(splitData[2]), splitData[3], splitData[4], splitData[5]);
                cardDeck.add(newCard);
            }
            catch(NumberFormatException error){}
        }
        readFile.close();

        //Creating the six Super Trump Card objects, and appending it into the deck
        SuperTrumpCard theGeologist = new SuperTrumpCard("The Geologist", "Change to trump category of your choice");
        cardDeck.add(theGeologist);
        SuperTrumpCard theGeophysicist = new SuperTrumpCard("The Geophysicist", "Change the trump category to Specific Gravity or throw magnetite");
        cardDeck.add(theGeophysicist);
        SuperTrumpCard theMineralogist = new SuperTrumpCard("The Mineralogist","Change the trump category to Cleavage");
        cardDeck.add(theMineralogist);
        SuperTrumpCard theGemmologist = new SuperTrumpCard("The Gemmologist", "Change the trump category to Hardness");
        cardDeck.add(theGemmologist);
        SuperTrumpCard thePetrologist = new SuperTrumpCard("The Petrologist", "Change the trump category to Crystal Abundance");
        cardDeck.add(thePetrologist);
        SuperTrumpCard theMiner = new SuperTrumpCard("The Miner", "Change the trump category to Economic Value");
        cardDeck.add(theMiner);

        //Welcome Message
        System.out.println("Welcome to the Mineral Supertrumps game!");

        //Asking for user input on the number of players, and storing it in variable playerCount
        int playerCount = 0;
        while (true) {
            try {
                Scanner gameStart = new Scanner(System.in);
                System.out.println("\nPlease enter the number of players.\nA minimum of 3 players is required, up to a maximum of 5 players.");
                playerCount = gameStart.nextInt();
                while (playerCount < 3 || playerCount > 5) {
                    System.out.println("You have entered an invalid number of players, please enter a number from 3 to 5!");
                    playerCount = gameStart.nextInt();
                }
                break;
            } catch (InputMismatchException error) {
                System.out.println("You have entered a wrong data type! Please enter an integer number (3,4,5.. etc)");
            }
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
        int totalCardCount = 60;
        int dealCard;
        for (int x = 0; x < playerCount; x++) {
            for (int y = 0; y < 8; y++) {
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(x).drawCard(cardDeck.get(dealCard));
                cardDeck.remove(dealCard);
                totalCardCount -= 1;
            }
        }

        System.out.println("\nThe cards have been dealt!\nThere are " +playerCount+" players.\nThe amount of cards left in the deck is: " + totalCardCount + "\nThe game is starting! ");
        System.out.println("\n*******************************************************************************************");

        int playerSelection = 0; //Variable based on player input for card selection from their hand
        ArrayList<String> finishedPlayerList = new ArrayList<String>(); //Arraylist to store finished players according to their ranking.
        int finishedPlayers = 0; //Variable used to count the number of finished players.
        int currentPlayer;
        int currentPlayerIndex;

        int playerPassCount = 0; //Variable used to count the number of players that has passed their turn.
        String currentTrump = null; //Variable based on current Trump category in play.
        String currentTrumpName = null; //Variable based on name of the current card in play.
        double currentDoubleValue = 0; //Variable based on the current double value of the Trump category of the card in play.
        String currentStringValue = "Nothing!"; //Variable based on the String values of some of the Trump category of the card in play.
        Boolean runGame = true; //Boolean Variable used to create an endless loop

        while (runGame) {
            //int x is used to keep count of the current player.
            for (int x = 0; x < playerCount; x++) {
                currentPlayer = x + 1;
                currentPlayerIndex = x;
                //IF statement used to determine if player is still playing the game, has not won the game yet.
                if (playerList.get(currentPlayerIndex).getPlayerGameStatus()) {
                    System.out.println("\nPlayer " + currentPlayer + "'s Turn! \n");

                    //IF/ELSE statement used to determine if the player has passed, and not allow them to play until all but one player has passed.
                    if (!playerList.get(currentPlayerIndex).getPlayerTurnStatus()) {
                        System.out.println("You are unable to get a turn! Please wait until all but one player has passed.");
                    }
                    else {
                        //Displays player hand at the start of their turn
                        System.out.println(playerList.get(currentPlayerIndex));

                        //IF/ELSE statement is to allow the player to start a new round of the card play,using variable currentTrumpName as a check.
                        //IF the player is the first player of a new round...
                        if (currentTrumpName == null || (playerPassCount == (playerCount - 1))) {

                            //IF/ELSE statement is to print corresponding messages according to why the player gets to play the first card of the new round...
                            //IF the player is the one who plays the first card of the whole game or after another player has won...
                            if(currentTrumpName == null){
                            System.out.println("\nYou get to play the first card!\nPlay a card by selecting the number");}

                            //ELSE every other player has passed, resetting variables for a new round...
                            else{
                                System.out.println("All other players have passed. You can play any card now!");
                                for (Player aPlayer : playerList) {
                                    aPlayer.setPlayerTurnStatus(true);
                                }
                                playerPassCount = 0;
                            }

                            //Asking for user input on which card to play, with error checking...
                            while (true) {
                                try {
                                    Scanner firstPlayCard = new Scanner(System.in);
                                    playerSelection = firstPlayCard.nextInt();
                                    while (playerSelection == 0 || playerSelection > playerList.get(currentPlayerIndex).getPlayerHand().size()) {
                                        System.out.println("You have entered an invalid card number, please enter a card number from 1 to " + playerList.get(currentPlayerIndex).getPlayerHand().size());
                                        playerSelection = firstPlayCard.nextInt();
                                    }
                                    break;
                                } catch (InputMismatchException error) {
                                    System.out.println("You have entered a wrong data type! Please enter a card number from your hand!");
                                }
                            }

                            //IF/ELSE statement used to determine if a Mineral or Trump card is played, and executes the card mechanics accordingly.
                            //IF Mineral card is played...
                            if (playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1) instanceof MineralCard){
                                int playCategory; // Variable used to store user selection on which Trump category is to be played.
                                while (true) {
                                    System.out.println("You have selected: " + playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));
                                    try{
                                        Scanner mineralInput = new Scanner(System.in);
                                        System.out.println("\nChoose a playing category:\n[1] : Hardness\n[2] : Specific Gravity\n[3] : Cleavage\n[4] : Crystal Abundance\n[5] : Economic Value");
                                        playCategory = mineralInput.nextInt();
                                        while(playCategory < 1 || playCategory > 5){
                                            System.out.println("You have entered an invalid number, Please enter a number from 1 to 5\nChoose a playing category:\n[1] : Hardness\n[2] : Specific Gravity\n[3] : Cleavage\n[4] : Crystal Abundance\n[5] : Economic Value");
                                            Scanner mineralInputError = new Scanner(System.in);
                                            playCategory = mineralInputError.nextInt();
                                        }
                                        break;
                                    } catch(InputMismatchException error){
                                        System.out.println("You have entered a wrong data type!");
                                    }
                                }

                                //Storing variables according to the properties of the Card. Explicit casting is used to allow methods for objects of MineralCard class to be called.
                                currentTrumpName = playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1).getMineralName();
                                currentTrump = changeTrump(playCategory);
                                currentDoubleValue = changeTrumpValue(playCategory, (MineralCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));
                                currentStringValue = changeTrumpStringValue(playCategory, (MineralCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));

                                //Displaying the card that the player has played for the turn and removing it from the player hand.
                                System.out.println("\nPlayer " + currentPlayer + " has played " + playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));
                                playerList.get(currentPlayerIndex).getPlayerHand().remove(playerSelection - 1);
                                }

                            //ELSE Trump card is played...
                            else {
                                currentTrumpName = playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1).getMineralName();

                                //Resetting all player turns, according to the game mechanics of playing a Trump card.
                                for (Player aPlayer : playerList) {
                                    aPlayer.setPlayerTurnStatus(true);
                                }

                                //Changing the Trump category in play according to the SuperTrump card properties.
                                currentTrump = ((SuperTrumpCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1)).getSuperTrumpCardCat(currentTrumpName,currentPlayerIndex,playerList.get(currentPlayerIndex),playerList.get(currentPlayerIndex).getPlayerHand().get(currentPlayerIndex));
                                currentDoubleValue = 0;
                                currentStringValue = "Nothing!";

                                if (currentTrump.equals("None")) {
                                    playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                                    System.out.println("Player " + currentPlayer + " has finished the game by playing the SuperTrump card 'The Geophysicist' with the Magnetite card!");
                                    finishedPlayerList.add("Player" + currentPlayer);
                                    finishedPlayers += 1;
                                    currentTrumpName = null;

                                    //IF statement used to determine how many players are left. One player remaining ends the game loop.
                                    if (finishedPlayers == playerCount - 1) {
                                        runGame = false;
                                    }
                                }
                                else{
                                //Displaying the card that the player has played for the turn and removing it from the player hand.
                                System.out.println("\nPlayer " + currentPlayer + " has played the Trump Card: " + currentTrumpName + "\n\nPlayer " + currentPlayer + " gets another turn! All players get their turns back as well!");
                                playerList.get(currentPlayerIndex).getPlayerHand().remove(playerSelection - 1);
                                playerPassCount = 0;
                                //Allowing the player to get another turn, as per game mechanics of playing a Trump card.
                                    x -= 1;
                                }
                            }
                            //IF statement used to determine if there are any cards left in the player's hand. If not, he/she wins the game.
                            if (playerList.get(currentPlayerIndex).getPlayerHand().isEmpty()) {
                                System.out.println("\nPlayer " + currentPlayer + " has finished the game!\nThe game is restarting, remaining players get ready!");
                                playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                                finishedPlayerList.add("Player" + currentPlayer);
                                finishedPlayers += 1;
                                currentTrumpName = null;
                                for (Player aPlayer : playerList) {
                                    aPlayer.setPlayerTurnStatus(true);
                                }
                                playerPassCount = 0;
                                //IF statement used to determine how many players are left. One player remaining ends the game loop.
                                if (finishedPlayers == playerCount - 1) {
                                    runGame = false;
                                }
                            }
                        }

                        //Else there is a card already in play...
                        else {
                            //IF/ELSE statement is used here to determine if the current Trump Category uses a String value or double value, and prints the properties of the current card in play.
                            //IF Current Trump Category uses a String value...
                            if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                                System.out.println("\nCurrent card in play is: " + currentTrumpName + ". The chosen trump category is: " + currentTrump + " with a current value of: " + currentStringValue);
                            }
                            //Else Current Trump Category uses a double value...
                            else {
                                System.out.println("\nCurrent card in play is: " + currentTrumpName + ". The chosen trump category is: " + currentTrump + " with a current value of: " + currentDoubleValue);
                            }

                            //Start by asking the player to play a card from their hand...

                            while (true) {
                                try {
                                    System.out.println("\nPlay a card by selecting the number or press 0 to Pass and draw a card!");
                                    Scanner nextPlayCard = new Scanner(System.in);
                                    playerSelection = nextPlayCard.nextInt();
                                    while (playerSelection > playerList.get(currentPlayerIndex).getPlayerHand().size()) {
                                        System.out.println("You have entered an invalid card number, please enter a card number from 1 to " + playerList.get(currentPlayerIndex).getPlayerHand().size() + " or enter 0 to pass.");
                                        Scanner nextPlayCardError = new Scanner(System.in);
                                        playerSelection = nextPlayCardError.nextInt();
                                    }
                                    break;
                                } catch (InputMismatchException error) {
                                    System.out.println("You have entered a wrong data type!");
                                }
                            }


                            // Variable decidingTrumpValue is used to store the Trump value of the card, to be used to determine if the value is higher or lower than the one in play
                            double decidingTrumpValue;

                            //IF/ELSE statement used to evaluate the player's decision of playing a card or passing.
                            //IF player has chosen to play a card...
                            if (playerSelection > 0 && playerSelection <= playerList.get(currentPlayerIndex).getPlayerHand().size()) {

                                //IF/ELSE statement used to determine if a Mineral or Trump card is played, and executes the card mechanics accordingly.
                                //IF Mineral card is played...
                                if (playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1) instanceof MineralCard){

                                    //Calculate the Trump value of the player's chosen card according to the Trump category in play, using method calTrumpValue
                                    decidingTrumpValue = calTrumpValue(currentTrump, (MineralCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));

                                    //WHILE statement is used to ensure that player plays a card that has a higher value than the one in play.
                                    while (decidingTrumpValue <= currentDoubleValue) {
                                        try{
                                            System.out.println("The card you have played has a lower value than the card in play! Please select another card.");
                                            Scanner nextPlayCard = new Scanner(System.in);
                                            playerSelection = nextPlayCard.nextInt();
                                            while (playerSelection > playerList.get(currentPlayerIndex).getPlayerHand().size()){
                                                System.out.println("You have entered an invalid card number, please enter a card number from 1 to " + playerList.get(currentPlayerIndex).getPlayerHand().size());
                                                playerSelection = nextPlayCard.nextInt();
                                            }
                                            if(playerSelection == 0){
                                                break;
                                            }
                                            else if(playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1) instanceof SuperTrumpCard){
                                                break;
                                            }
                                            else{
                                                decidingTrumpValue = calTrumpValue(currentTrump, (MineralCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));
                                            }
                                        } catch (InputMismatchException error){
                                            System.out.println("You have entered a wrong data type! Please enter a card number from your hand!");
                                        }
                                    }

                                    if(playerSelection == 0) {
                                        //IF/ELSE statement used to determine if there are any cards left in the deck.
                                        //IF there are cards left in the deck...
                                        if (totalCardCount != 0) {
                                            dealCard = randInt.nextInt(totalCardCount);
                                            playerList.get(currentPlayerIndex).drawCard(cardDeck.get(dealCard));
                                            cardDeck.remove(dealCard);
                                            totalCardCount -= 1;

                                            //Player turn status is changed to false as they forfeit their turn by drawing a card, as per game mechanics.
                                            playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
                                            playerPassCount += 1;
                                            System.out.println("\nPlayer " + currentPlayer + " has passed and drew a card! \nThe amount of cards left in the deck is: " + totalCardCount + "\nPlease wait for your next turn!");
                                        }

                                        //ELSE there are no more cards left in the deck...
                                        else {
                                            playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
                                            playerPassCount += 1;
                                            System.out.println("\nThere are no more cards left in the deck! Please wait for your next turn.");
                                        }
                                    }
                                    else if(playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1) instanceof SuperTrumpCard){
                                        currentTrumpName = playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1).getMineralName();

                                        //Resetting all player turns, according to the game mechanics of playing a Trump card.
                                        for (Player aPlayer : playerList) {
                                            aPlayer.setPlayerTurnStatus(true);
                                        }

                                        //Changing the Trump category in play according to the SuperTrump card properties.
                                        currentTrump = ((SuperTrumpCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1)).getSuperTrumpCardCat(currentTrumpName,currentPlayerIndex,playerList.get(currentPlayerIndex),playerList.get(currentPlayerIndex).getPlayerHand().get(currentPlayerIndex));
                                        currentDoubleValue = 0;
                                        currentStringValue = "Nothing!";

                                        if (currentTrump.equals("None")) {
                                            playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                                            System.out.println("Player " + currentPlayer + " has finished the game by playing the SuperTrump card 'The Geophysicist' with the Magnetite card!");
                                            finishedPlayerList.add("Player" + currentPlayer);
                                            finishedPlayers += 1;
                                            currentTrumpName = null;

                                            //IF statement used to determine how many players are left. One player remaining ends the game loop.
                                            if (finishedPlayers == playerCount - 1) {
                                                runGame = false;
                                            }
                                        }
                                        else{//Displaying the card that the player has played for the turn and removing it from the player hand.
                                            System.out.println("\nPlayer " + currentPlayer + " has played the Trump Card: " + currentTrumpName + "\n\nPlayer " + currentPlayer + " gets another turn! All players get their turns back as well!");
                                            playerList.get(currentPlayerIndex).getPlayerHand().remove(playerSelection - 1);
                                            playerPassCount = 0;
                                            //Allowing the player to get another turn, as per game mechanics of playing a Trump card.
                                            x -= 1;
                                        }
                                    }

                                    else{
                                    //Updating variables for Trump Name and Value
                                    currentTrumpName = playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1).getMineralName();
                                    currentDoubleValue = decidingTrumpValue;
                                    currentStringValue = changeTrumpStringValAuto(currentTrump, (MineralCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));

                                    //Displaying the card that the player has played for the turn and removing it from the player hand.
                                    System.out.println("\nPlayer " + currentPlayer + " has played " + playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection - 1));
                                    playerList.get(currentPlayerIndex).getPlayerHand().remove(playerSelection - 1);
                                    }
                                }

                                //ELSE Trump card is played...
                                else {
                                    currentTrumpName = playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1).getMineralName();

                                    //Resetting all player turns, according to the game mechanics of playing a Trump card.
                                    for (Player aPlayer : playerList) {
                                        aPlayer.setPlayerTurnStatus(true);
                                    }

                                    //Changing the Trump category in play according to the SuperTrump card properties.
                                    currentTrump = ((SuperTrumpCard)playerList.get(currentPlayerIndex).getPlayerHand().get(playerSelection-1)).getSuperTrumpCardCat(currentTrumpName,currentPlayerIndex,playerList.get(currentPlayerIndex),playerList.get(currentPlayerIndex).getPlayerHand().get(currentPlayerIndex));
                                    currentDoubleValue = 0;
                                    currentStringValue = "Nothing!";

                                    if (currentTrump.equals("None")) {
                                        playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                                        System.out.println("Player " + currentPlayer + " has finished the game by playing the SuperTrump card 'The Geophysicist' with the Magnetite card!");
                                        finishedPlayerList.add("Player" + currentPlayer);
                                        finishedPlayers += 1;
                                        currentTrumpName = null;

                                        //IF statement used to determine how many players are left. One player remaining ends the game loop.
                                        if (finishedPlayers == playerCount - 1) {
                                            runGame = false;
                                        }
                                    }
                                    else{
                                    //Displaying the card that the player has played for the turn and removing it from the player hand.
                                    System.out.println("\nPlayer " + currentPlayer + " has played the Trump Card: " + currentTrumpName + "\n\nPlayer " + currentPlayer + " gets another turn! All players also get their turns back!");
                                    playerList.get(currentPlayerIndex).getPlayerHand().remove(playerSelection - 1);
                                    playerPassCount = 0;
                                        //Allowing the player to get another turn, as per game mechanics of playing a Trump card, if he/she still has cards in the hand.
                                        x -= 1;
                                    }
                                }

                                //IF statement used to determine if there are any cards left in the player's hand. If not, he/she wins the game.
                                if (playerList.get(currentPlayerIndex).getPlayerHand().isEmpty()) {
                                    System.out.println("\nPlayer " + currentPlayer + " has finished the game!\nThe game is restarting, remaining players get ready!");
                                    playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                                    finishedPlayerList.add("Player" + currentPlayer);
                                    finishedPlayers += 1;
                                    currentTrumpName = null;
                                    for (Player aPlayer : playerList) {
                                        aPlayer.setPlayerTurnStatus(true);
                                    }
                                    playerPassCount = 0;

                                    //IF statement used to determine how many players are left. One player remaining ends the game loop.
                                    if (finishedPlayers == playerCount - 1) {
                                        runGame = false;
                                    }
                                }
                            }
                            //ELSE player chooses to draw a card
                            else {
                                //IF/ELSE statement used to determine if there are any cards left in the deck.
                                //IF there are cards left in the deck...
                                if (totalCardCount != 0) {
                                    dealCard = randInt.nextInt(totalCardCount);
                                    playerList.get(currentPlayerIndex).drawCard(cardDeck.get(dealCard));
                                    cardDeck.remove(dealCard);
                                    totalCardCount -= 1;

                                    //Player turn status is changed to false as they forfeit their turn by drawing a card, as per game mechanics.
                                    playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
                                    playerPassCount += 1;
                                    System.out.println("\nPlayer " + currentPlayer + " has passed and drew a card! \nThe amount of cards left in the deck is: " + totalCardCount + "\nPlease wait for your next turn!");
                                }

                                //ELSE there are no more cards left in the deck...
                                else {
                                    playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
                                    playerPassCount += 1;
                                    System.out.println("\nThere are no more cards left in the deck! Please wait for your next turn.");
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("The game has finished! \nThe overall winner is: " + finishedPlayerList.get(0) + "\nHere are the runner ups:");
    }

    //Method to return the Trump Category based on user selection. Returns a string value of the Trump Category
    private static String changeTrump(int playCategory){
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

    /*
    Method to return value of the trump category of the card the user has played, using double values.
    For trump categories that use String values, the values are passed through the MineralCard Class methods of getMineralCleavageValue, getMineralCaValue, and getEvValue.
    These methods return a double value based on the given ranking of the String values, which is then assigned to the variable currentTrumpValue in this method, to be used as comparison values in the main.
    */
    private static double changeTrumpValue(int playCategory, MineralCard playedCard){
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

    /*
    Method to return the value of the trump category of the card the user has played, using String values.
    For trump categories that use double values, the method will return null values for the variable currentStringValue.
     */
    private static String changeTrumpStringValue(int playCategory, MineralCard playedCard){
        String currentStringValue = null;
        switch (playCategory) {
            case 1:
                currentStringValue = null;
                break;
            case 2:
                currentStringValue = null;
                break;
            case 3:
                currentStringValue = playedCard.getMineralCleavage();
                break;
            case 4:
                currentStringValue = playedCard.getMineralCA();
                break;
            case 5:
                currentStringValue = playedCard.getMineralEcoValue();
                break;}
        return currentStringValue;
    }

    private static String changeTrumpStringValAuto(String currentTrump, MineralCard playedCard){
        String currentStringValue = null;
        switch(currentTrump){
            case "Hardness":
                currentStringValue = null;
                break;
            case "Specific Gravity":
                currentStringValue = null;
                break;
            case "Cleavage":
                currentStringValue = playedCard.getMineralCleavage();
                break;
            case "Crystal Abundance":
                currentStringValue = playedCard.getMineralCA();
                break;
            case "Economic Value":
                currentStringValue = playedCard.getMineralEcoValue();
        }
        return currentStringValue;
    }

    //Method to return a double value based on the card played.
    private static double calTrumpValue(String currentTrump, MineralCard playedCard){
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
            case "Crystal Abundance":
                decidingTrumpValue = playedCard.getMineralCaValue(playedCard.getMineralCA());
                break;
            case "Economic Value":
                decidingTrumpValue = playedCard.getEvValue(playedCard.getMineralEcoValue());
        }
        return decidingTrumpValue;
    }
}