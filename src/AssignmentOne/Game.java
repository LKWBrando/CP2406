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

        System.out.println(cardDeck.get(0).getMineralName());
        System.out.println(cardDeck.get(0).getMineralHD());
        System.out.println(cardDeck.get(0).getMineralSG());
        System.out.println(cardDeck.get(0).getMineralCleavage());
        System.out.println(cardDeck.get(0).getMineralCA());
        System.out.println(cardDeck.get(0).getMineralEcoValue());

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

        for (int i = 0; i < playerList.size(); i++) {
            System.out.println(playerList.get(i));
        }

        System.out.println("The amount of cards left in the deck is: " + totalCardCount + "\nThe game is starting! ");
        System.out.println("*******************************************************************************************");

        int playerSelection;
        int finishedPlayers = 0;
        int playCategory = 0;
        ArrayList <String>finishedPlayerList = new <String> ArrayList();
        String currentTrump = null;
        double currentTrumpValue = 0;
        Boolean runGame = true;

        while (runGame){
        for (int x = 0; x < playerCount; x++) {
            if (playerList.get(x).getPlayerStatus()) {
                System.out.println("Player " + (x+1) + "'s Turn! \n" + playerList.get(x));
                Scanner userInputGameMenu = new Scanner(System.in);
                System.out.println("Play a card by selecting the number or press 0 to Pass and draw a card!");
                playerSelection = userInputGameMenu.nextInt();
                /*
                if (currentTrump == null);{}
                System.out.println("You are free to play any cards! Choose a playing category");
                Scanner userInputPlayCat = new Scanner(System.in);
                System.out.println("[1] Hardness, [2] SG, [3] Cleavage, [4] CA, [5] EV");
                playCategory = userInputPlayCat.nextInt();
                    switch(playCategory){
                        case 1: currentTrump = "Hardness";
                        case 2: currentTrump = "Specific Gravity";
                        case 3: currentTrump = "Cleavage";
                        case 4: currentTrump = "Crystal Abundance";
                        case 5: currentTrump = "Economic Value";
                    }*/

                    if (playerSelection >0 && playerSelection<=playerList.get(x).getPlayerHand().size()){

                        System.out.println("Player " + (x+1) + " has played " + playerList.get(x).getPlayerHand().get(playerSelection-1));
                        playerList.get(x).getPlayerHand().remove(playerSelection-1);
                        if (playerList.get(x).getPlayerHand().isEmpty()) {
                            System.out.println("Player " + (x+1) + "has finished the game!");
                            playerList.get(x).setPlayerStatus(false);
                            finishedPlayerList.add("Player"+x);
                            finishedPlayers += 1;
                            if (finishedPlayers == playerCount - 1){
                                runGame=false;
                            }
                        }
                    }
                    else{
                        if (totalCardCount != 0){
                            dealCard = randInt.nextInt(totalCardCount);
                            playerList.get(x).drawCard(cardDeck.get(dealCard));
                            cardDeck.remove(dealCard);
                            totalCardCount -= 1;}
                        else{
                        System.out.println("There are no more cards left in the deck! Wait for your next turn.");}
                    }
                }
            }
        }
        System.out.println("The game has finished! \nThe overall winner is: " + finishedPlayerList.get(0) + "\nHere are the runner ups:");
        for(int i = 1; i<=finishedPlayerList.size(); i++);
    }
}