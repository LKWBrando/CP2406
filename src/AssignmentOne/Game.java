package AssignmentOne;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) throws IOException {
        ArrayList<Card> cardDeck = new ArrayList<Card>();

        Scanner inputFile = new Scanner(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\src\\AssignmentOne\\card.txt"));
        while (inputFile.hasNextLine()) {
            String[] fileLine = inputFile.nextLine().split(",");
            try {
                double convMinHD = Double.parseDouble(fileLine[1]);
                double convMinSG = Double.parseDouble(fileLine[2]);
                Card newCard = new Card(fileLine[0], convMinHD, convMinSG, fileLine[3], fileLine[4], fileLine[5]);
                cardDeck.add(newCard);
            }
            catch (NumberFormatException error){}
        }
        inputFile.close();

        int playerCount = 0;
        Scanner gameStart = new Scanner(System.in);
        System.out.println("Please enter the number of players (Min 3, Max 5)");
        playerCount = gameStart.nextInt();

        while (playerCount < 3 || playerCount > 5) {
            System.out.println("You have entered an invalid response, please enter a number from 3 to 5");
            playerCount = gameStart.nextInt();
        }

        ArrayList<Player> playerList = new ArrayList<Player>();
        int newPlayerCount = 1;
        for (int x = 1; x <= playerCount; x++) {
            Player newPlayer = new Player(newPlayerCount);
            newPlayerCount += 1;
            playerList.add(newPlayer);
        }

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

        int playerSelection;
        int finishedPlayers = 0;
        int playCategory;
        int lastPlayerAction =0;
        ArrayList <String>finishedPlayerList = new <String> ArrayList();
        String currentTrump = null;
        String currentTrumpName = null;
        double currentTrumpValue = 0;
        String displayCurrentTrumpValue = null;
        Boolean runGame = true;

        while (runGame){
        for (int x = 0; x < playerCount; x++) {
            if (playerList.get(x).getPlayerStatus()) {
                System.out.println("\nPlayer " + (x + 1) + "'s Turn! \n" + playerList.get(x));
                if (currentTrumpName == null) {
                    System.out.println("\nYou get to play the first card!");
                    Scanner firstPlayCard = new Scanner(System.in);
                    System.out.println("Play a card by selecting the number");
                    playerSelection = firstPlayCard.nextInt();
                    Scanner userInputPlayCat = new Scanner(System.in);
                    System.out.println("You have selected: " + playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName() + "\nChoose a playing category: [1] Hardness, [2] SG, [3] Cleavage, [4] CA, [5] EV");
                    playCategory = userInputPlayCat.nextInt();
                    currentTrumpName = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName();
                    switch (playCategory) {
                        case 1:
                            currentTrump = "Hardness";
                            currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralHD();
                            break;
                        case 2:
                            currentTrump = "Specific Gravity";
                            currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralSG();
                            break;
                        case 3:
                            currentTrump = "Cleavage";
                            currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCleavageValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCleavage());
                            displayCurrentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralCleavage();
                            break;
                        case 4:
                            currentTrump = "Crystal Abundance";
                            currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCaValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCA());
                            displayCurrentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralCA();
                            break;
                        case 5:
                            currentTrump = "Economic Value";
                            currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getEvValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralEcoValue());
                            displayCurrentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralEcoValue();
                            break;
                    }
                    lastPlayerAction = x;
                    System.out.println("\nPlayer " + (x + 1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection - 1));
                } else {
                    if(lastPlayerAction == x){
                        System.out.println("\nNo other player has managed to play a card! You are free to play any cards! \nPlay a card by selecting the number.");
                        Scanner passedTurns = new Scanner(System.in);
                        playerSelection = passedTurns.nextInt();
                        Scanner userInputPlayCat = new Scanner(System.in);
                        System.out.println("You have selected: " + playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName());
                        System.out.println("Choose a playing category: [1] Hardness, [2] SG, [3] Cleavage, [4] CA, [5] EV");
                        playCategory = userInputPlayCat.nextInt();
                        currentTrumpName = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralName();
                        switch (playCategory) {
                            case 1:
                                currentTrump = "Hardness";
                                currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralHD();
                                break;
                            case 2:
                                currentTrump = "Specific Gravity";
                                currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralSG();
                                break;
                            case 3:
                                currentTrump = "Cleavage";
                                currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCleavageValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCleavage());
                                displayCurrentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralCleavage();
                                break;
                            case 4:
                                currentTrump = "Crystal Abundance";
                                currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCaValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCA());
                                displayCurrentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralCA();
                                break;
                            case 5:
                                currentTrump = "Economic Value";
                                currentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getEvValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralEcoValue());
                                displayCurrentTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralEcoValue();
                                break;
                        }
                        lastPlayerAction = x;
                        System.out.println("\nPlayer " + (x + 1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection - 1));
                    }
                    if(currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")){
                    System.out.println("\nCurrent card in play is: " + currentTrumpName + " . The chosen trump category is:" + currentTrump + " with a current value of: " + displayCurrentTrumpValue);}
                    else{
                        System.out.println("\nCurrent card in play is: " + currentTrumpName + " . The chosen trump category is:" + currentTrump + " with a current value of: " +currentTrumpValue);}

                    Scanner userInputGameMenu = new Scanner(System.in);
                    System.out.println("\nPlay a card by selecting the number or press 0 to Pass and draw a card!");
                    playerSelection = userInputGameMenu.nextInt();
                    double decidingTrumpValue = 0;
                    while (playerSelection > 0 && playerSelection <= playerList.get(x).getPlayerHand().size()) {
                        switch (currentTrump) {
                            case "Hardness":
                                decidingTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralHD();
                                break;
                            case "Specific Gravity":
                                decidingTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralSG();
                                break;
                            case "Cleavage":
                                decidingTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCleavageValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCleavage());
                                break;
                            case "Crystal Abuandance":
                                decidingTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCaValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralCA());
                                break;
                            case "Economic Value":
                                decidingTrumpValue = playerList.get(x).getPlayerHand().get(playerSelection - 1).getEvValue(playerList.get(x).getPlayerHand().get(playerSelection - 1).getMineralEcoValue());
                        }
                        if (decidingTrumpValue <= currentTrumpValue) {
                            System.out.println("The card you have played has a lower value in the selected category! Please select another card.");
                            Scanner userNextInputGameMenu = new Scanner(System.in);
                            playerSelection = userNextInputGameMenu.nextInt();
                        } else {
                            currentTrumpName = playerList.get(x).getPlayerHand().get(playerSelection-1).getMineralName();
                            currentTrumpValue = decidingTrumpValue;
                            System.out.println("Player " + (x + 1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection - 1));
                            playerList.get(x).getPlayerHand().remove(playerSelection - 1);
                            lastPlayerAction = x;
                            if (playerList.get(x).getPlayerHand().isEmpty()) {
                                System.out.println("Player " + (x + 1) + "has finished the game!");
                                playerList.get(x).setPlayerStatus(false);
                                finishedPlayerList.add("Player" + x);
                                finishedPlayers += 1;
                                if (finishedPlayers == playerCount - 1) {
                                    runGame = false;
                                }
                            } else {
                                if (totalCardCount != 0) {
                                    dealCard = randInt.nextInt(totalCardCount);
                                    playerList.get(x).drawCard(cardDeck.get(dealCard));
                                    cardDeck.remove(dealCard);
                                    totalCardCount -= 1;
                                } else {
                                    System.out.println("There are no more cards left in the deck! Wait for your next turn.");
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
        }
        System.out.println("The game has finished! \nThe overall winner is: " + finishedPlayerList.get(0) + "\nHere are the runner ups:");
        for(int i = 1; i<=finishedPlayerList.size(); i++);
    }
}