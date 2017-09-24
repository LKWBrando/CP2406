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
import java.util.*;

public class GameGui extends JFrame implements ActionListener{
    private JPanel header, body,secondBody, displayTrump;
    private JLabel headerTitle,roundCount, playerTurn, footer;
    private JButton pcThree, pcFour, pcFive;
    private ArrayList<JButton> imageList = new ArrayList<JButton>();
    private ArrayList<String> imagePathString = new ArrayList<String>();
    private ArrayList<JButton> playerHand = new ArrayList<JButton>();
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Map<Card, JButton> cardImageMap = new HashMap<Card, JButton>();
    public int playerCount;
    private Boolean initalMenu = true;

    public GameGui() throws IOException {
        setTitle("SuperTrump Card Game");
        setSize(1920, 1080);
        setLayout(new GridLayout(5, 1));

        header = new JPanel();
        header.setPreferredSize(new Dimension(1920, 150));
        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        header.add(headerTitle);
        add(header);

        secondBody = new JPanel();
        secondBody.setPreferredSize(new Dimension(1920, 150));
        roundCount = new JLabel("Please select the Number of Players", SwingConstants.CENTER);
        secondBody.add(roundCount);
        add(secondBody);

        body = new JPanel();
        body.setPreferredSize(new Dimension(1920, 600));
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
        displayTrump.setPreferredSize(new Dimension(1920, 150));
        add(displayTrump);

        footer = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        add(footer);

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

        for (int i = 0; i < imagePathString.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imagePathString.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(150, 400, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            newButton.addActionListener(this);
            imageList.add(newButton);
        }

        for (int i = 0; i < cardDeck.size(); i++) {
            cardImageMap.put(cardDeck.get(i), imageList.get(i));
        }
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void displayPlayerHand(){
        for (int i = 0 ; i < playerList.get(0).getPlayerHand().size(); i++){
            body.add(cardImageMap.get(playerList.get(0).getPlayerHand().get(i))).setPreferredSize(new Dimension(150,400));
            revalidate();
            repaint();
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
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
            default:
                break;
        }
        body.removeAll();
        revalidate();
        repaint();

        roundCount.setText("You have selected " + playerCount + " players!");

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
    }

    public static void main(String[] args) throws IOException {
        GameGui trumpGui = new GameGui();
        trumpGui.setVisible(true);
        trumpGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
