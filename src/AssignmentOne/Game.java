package AssignmentOne;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

public class Game {
    public static void main(String[] args) throws IOException{
        int playerCount = 0;
        int count = 1;
        int totalCardCount = 54;
        int dealCard = 0;
        int playerTurn = 0;
        int playerSelection = 0;

        ArrayList <Card> cardDeck = new ArrayList<Card>();

        Scanner inputFile = new Scanner(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\src\\AssignmentOne\\card.txt"));
        while (inputFile.hasNextLine()){
            String[] fileLine = inputFile.nextLine().split(",");
            Card newCard = new Card(count, fileLine[0],fileLine[1], fileLine[2], fileLine[3], fileLine[4], fileLine[5]);
            count += 1;
            cardDeck.add(newCard);
        }
        inputFile.close();
        cardDeck.remove(0);

        Scanner gameStart = new Scanner(System.in);
        System.out.println("Please enter the number of players (Min 3, Max 5)");
        playerCount = gameStart.nextInt();

        while (playerCount <3 || playerCount >5){
            System.out.println("You have entered an invalid response, please enter a number from 3 to 5");
            playerCount = gameStart.nextInt();}

        ArrayList <Player> playerList = new ArrayList<Player>();
        int newPlayerCount = 1;

        for (int x = 1; x<=playerCount; x++){
            Player newPlayer = new Player(newPlayerCount);
            newPlayerCount += 1;
            playerList.add(newPlayer);}

        Random randInt = new Random();

        for (int x = 1; x <=playerCount; x++){
            for(int y = 0; y<8; y++){
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(playerTurn).drawCard(cardDeck.get(dealCard));
                cardDeck.remove(dealCard);
                totalCardCount -= 1;}
            playerTurn += 1;}

        for (int i = 0; i<playerList.size(); i++){
            System.out.println(playerList.get(i));
        }

        System.out.println("The amount of cards left in the deck is: " + totalCardCount +"\n The game is starting! ");
        System.out.println("*******************************************************************************************");

        /*
        for (int x =0; x<=playerCount; x++){
            while (playerList.get(x).getPlayerStatus() == true){
                System.out.println("Player " + x + "'s Turn! Play or Draw a card!");
                System.out.println(playerList.get(x).getPlayerHand());
            }*/




    }
}
