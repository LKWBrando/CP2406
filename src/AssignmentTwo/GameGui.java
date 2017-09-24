package AssignmentTwo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GameGui extends JFrame implements ActionListener{
    private JPanel header, body,secondBody, displayTrump;
    private JLabel headerTitle,roundCount, playerTurn, footer;
    private JButton pcThree, pcFour, pcFive;
    private ArrayList<JButton> imageList = new ArrayList<JButton>();
    private ArrayList<String> imagePathString = new ArrayList<String>();
    private ArrayList<JButton> playerHand = new ArrayList<JButton>();
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    public int playerCount;
    private Boolean initalMenu = true;

    public GameGui() throws IOException {
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

        File[] imageFiles = new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images").listFiles();
        int fileCount = 0;
        for (File fileName : imageFiles) {
            imagePathString.add(fileName.getName());
            fileCount += 1;
        }
        System.out.println(imagePathString);

        for (int i = 0; i < imagePathString.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imagePathString.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(200, 330, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            newButton.addActionListener(this);
            imageList.add(newButton);
        }

        Map<Card, JButton> cardImageMap = new HashMap<Card, JButton>();
        for (int i = 0; i < cardDeck.size(); i++) {
            cardImageMap.put(cardDeck.get(i), imageList.get(i));
        }

        ArrayList<JButton> imageList = new ArrayList<JButton>();
        for (int i = 0; i < imagePathString.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imagePathString.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(200, 330, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            imageList.add(newButton);
        }

        setTitle("SuperTrump Card Game");
        setSize(1980, 782);
        setLayout(new GridLayout(5, 1));

        header = new JPanel();
        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        header.add(headerTitle);
        add(header);

        secondBody = new JPanel();
        roundCount = new JLabel("Please select the Number of Players", SwingConstants.CENTER);
        secondBody.add(roundCount);
        add(secondBody);

        body = new JPanel();
        pcThree = new JButton("3 Players!");
        pcThree.addActionListener(this);
        pcFour = new JButton("4 Players!");
        pcFour.addActionListener(this);
        pcFive = new JButton("5 Players!");
        pcFive.addActionListener(this);
        body.add(pcThree);
        body.add(pcFour);
        body.add(pcFive);
        add(body);

        displayTrump = new JPanel(new GridLayout(1, 5));
        add(displayTrump);

        footer = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        add(footer);


    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();

        while (initalMenu) {
            switch (clickedButton.getText()) {
                case "3 Players!":
                    setPlayerCount(3);
                    break;
                case "4 Players!":
                    setPlayerCount(4);
                    break;
                case "5 Players!":
                    setPlayerCount(5);
                    break;
            }
            initalMenu = false;
            roundCount.setText("You have selected " + playerCount + " players!");
            body.removeAll();

            //Creating an arraylist to store the Player objects, based on the amount of players entered
            int playerListCount = 1;
            for (int x = 1; x <= playerCount; x++) {
                Player newPlayer = new Player(playerListCount);
                playerListCount += 1;
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
            revalidate();
            repaint();
        }
        int playerSelection = 0; //Variable based on player input for card selection from their hand
        ArrayList<Integer> finishedPlayerList = new ArrayList<Integer>(); //Arraylist to store finished players according to their ranking.
        int finishedPlayers = 0; //Variable used to count the number of finished players.
        int roundCount = 0; //Variable used to count the number of rounds
        int currentPlayer;//Variable used to indicate the current player.
        int currentPlayerIndex;//Variable used to indicate the arraylist index of the current player.
        int playerPassCount = 0; //Variable used to count the number of players that has passed their turn.
        String currentTrump = null; //Variable based on current Trump category in play.
        String currentTrumpName = null; //Variable based on name of the current card in play.
        double currentDoubleValue = 0; //Variable based on the current double value of the Trump category of the card in play.
        String currentStringValue = "Nothing!"; //Variable based on the String values of some of the Trump category of the card in play.
        Boolean runGame = true; //Boolean Variable used to create an endless loop

        while (true){
            for (int x = 0; x < (playerCount + finishedPlayers); x++){
                currentPlayer = x + 1;
                currentPlayerIndex = x;
                if (playerList.get(currentPlayerIndex).getPlayerGameStatus()) {
                    System.out.println("\nPlayer " + currentPlayer + "'s Turn! \n");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GameGui trumpGui = new GameGui();
        trumpGui.setVisible(true);
        trumpGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
