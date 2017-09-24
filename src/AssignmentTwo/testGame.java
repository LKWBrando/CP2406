package AssignmentTwo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class testGame {
    public static void main(String[] args) throws IOException {
        //Creating an Arraylist to hold Card objects
        ArrayList<Card> cardDeck = new ArrayList<Card>();

        //Reading from the card.txt file, taking each line in the txt file, splitting it, and appending seperated values as variables of a new Card object
        FileReader readFile = new FileReader(".\\card.txt");
        BufferedReader newFileLine = new BufferedReader(readFile);
        String nextLine;
        while ((nextLine = newFileLine.readLine()) != null) {
            String[] splitData = nextLine.split(",");
            try {
                MineralCard newCard = new MineralCard(splitData[0], Double.parseDouble(splitData[1]), Double.parseDouble(splitData[2]), splitData[3], splitData[4], splitData[5]);
                cardDeck.add(newCard);
            } catch (NumberFormatException error) {
            }
        }
        readFile.close();

        //Creating the six Super Trump Card objects, and appending it into the deck
        SuperTrumpCard theMiner = new SuperTrumpCard("The Miner", "Change the trump category to Economic Value");
        cardDeck.add(theMiner);
        SuperTrumpCard thePetrologist = new SuperTrumpCard("The Petrologist", "Change the trump category to Crystal Abundance");
        cardDeck.add(thePetrologist);
        SuperTrumpCard theGemmologist = new SuperTrumpCard("The Gemmologist", "Change the trump category to Hardness");
        cardDeck.add(theGemmologist);
        SuperTrumpCard theMineralogist = new SuperTrumpCard("The Mineralogist", "Change the trump category to Cleavage");
        cardDeck.add(theMineralogist);
        SuperTrumpCard theGeophysicist = new SuperTrumpCard("The Geophysicist", "Change the trump category to Specific Gravity or throw magnetite");
        cardDeck.add(theGeophysicist);
        SuperTrumpCard theGeologist = new SuperTrumpCard("The Geologist", "Change to trump category of your choice");
        cardDeck.add(theGeologist);

        //Reading the image file names and storing the string values
        ArrayList<String> imagePathString = new ArrayList<String>();
        ArrayList<Integer> fileIndexList = new ArrayList<Integer>();
        File[] imageFiles = new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images").listFiles();
        int fileCount = 0;
        for (File fileName : imageFiles) {
            imagePathString.add(fileName.getName());
            fileIndexList.add(fileCount);
            fileCount += 1;
        }
        System.out.println(imagePathString);

        Map<Integer, Card> cardImageMap = new HashMap<Integer, Card>();
        for (int i = 0; i < cardDeck.size(); i++) {
            cardImageMap.put(fileIndexList.get(i), cardDeck.get(i));
        }

        ArrayList<JButton> imageList = new ArrayList<JButton>();
        for (int i = 0; i < imagePathString.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imagePathString.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(200, 330, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            imageList.add(newButton);
        }

        GameGui trumpGui = new GameGui();
        trumpGui.setVisible(true);
        trumpGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int playerCount = 0;
        playerCount = trumpGui.getPlayerCount();
        ArrayList<Player> playerList = new ArrayList<Player>();
        int playerListCount = 1;
        for (int x = 1; x <= playerCount; x++) {
            Player newPlayer = new Player(playerListCount);
            playerListCount += 1;
            playerList.add(newPlayer);
        }

        Random randInt = new Random();
        int totalCardCount = 60;
        int dealCard;

        for (int x = 0; x < playerCount; x++) {
            for (int y = 0; y < 8; y++) {
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(x).drawCard(cardDeck.get(dealCard));
                trumpGui.setPlayerHand(imageList.get(dealCard));
                imageList.remove(dealCard);
                cardDeck.remove(dealCard);
                totalCardCount -= 1;
            }
        }
    }
}
