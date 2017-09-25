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

public class GameGui2 extends JFrame implements ActionListener {
    private JPanel body,displayTrump, displayInfo;
    private JLabel headerTitle, secondHeader, displayCurrentCard, footer;
    private JButton pcThree, pcFour, pcFive, hardness, sGravity, cleavage, cAbundance, EValue, passTurn;
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private ArrayList<String> imagePathString = new ArrayList<String>();
    private ArrayList<JButton> imageList = new ArrayList<JButton>();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Map<Card, JButton> cardToButtonMap = new HashMap<Card, JButton>();
    private Map<JButton, Card> buttonToCardMap = new HashMap<JButton, Card>();
    private int playerCount = 0;

    public GameGui2 () throws IOException {
        setTitle("SuperTrump Card Game");
        setSize(1920, 1080);
        setLayout(new GridLayout(5, 1));

        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        headerTitle.setPreferredSize(new Dimension(1920, 80));
        add(headerTitle);

        secondHeader = new JLabel("Please select the number of players.", SwingConstants.CENTER);
        headerTitle.setPreferredSize(new Dimension(1920, 100));
        add(secondHeader);

        body = new JPanel();
        body.setPreferredSize(new Dimension(1920, 720));
        pcThree = new JButton("3 Players");
        pcThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 3;
                body.removeAll();
                revalidate();
                repaint();
            }
        });
        pcFour = new JButton("4 Players");
        pcFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 4;
                body.removeAll();
                revalidate();
                repaint();
            }
        });
        pcFive = new JButton("5 Players");
        pcFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 5;
                body.removeAll();
                revalidate();
                repaint();
            }
        });

        body.add(pcThree);
        body.add(pcFour);
        body.add(pcFive);
        add(body);

        displayInfo = new JPanel();
        displayInfo.setPreferredSize(new Dimension(1920, 100));
        add(displayInfo);

        footer = new JLabel("Welcome to the Mineral SuperTrumps game!", SwingConstants.CENTER);
        footer.setPreferredSize(new Dimension(1920, 80));
        add(footer);

        //Reading file
        FileReader readFile = new FileReader("C:\\Users\\user\\IdeaProjects\\CP2406\\card.txt");
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

        SuperTrumpCard theMiner = new SuperTrumpCard("The Miner", "Change the trump category to Economic Value");
        cardDeck.add(theMiner);
        SuperTrumpCard thePetrologist = new SuperTrumpCard("The Petrologist", "Change the trump category to Crystal Abundance");
        cardDeck.add(thePetrologist);
        SuperTrumpCard theGemmologist = new SuperTrumpCard("The Gemmologist", "Change the trump category to Hardness");
        cardDeck.add(theGemmologist);
        SuperTrumpCard theMineralogist = new SuperTrumpCard("The Mineralogist","Change the trump category to Cleavage");
        cardDeck.add(theMineralogist);
        SuperTrumpCard theGeophysicist = new SuperTrumpCard("The Geophysicist", "Change the trump category to Specific Gravity or throw magnetite");
        cardDeck.add(theGeophysicist);
        SuperTrumpCard theGeologist = new SuperTrumpCard("The Geologist", "Change to trump category of your choice");
        cardDeck.add(theGeologist);

        File[] imageFiles = new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images").listFiles();
        for (File fileName : imageFiles) {
            imagePathString.add(fileName.getName());
        }
        //Creating Jbuttons based on mineral images
        for (int i = 0; i < cardDeck.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imagePathString.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(150, 400, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            newButton.addActionListener(this);
            imageList.add(newButton);
        }
        //Mapping
        for(int i = 0; i<cardDeck.size(); i++){
            cardToButtonMap.put(cardDeck.get(i), imageList.get(i));
            buttonToCardMap.put(imageList.get(i), cardDeck.get(i));
        }


    }

        public void actionPerformed (ActionEvent e){
            JButton clickedButton = (JButton) e.getSource();
    }

    public static void main(String[] args) throws IOException {
        GameGui2 newTest = new GameGui2();
        newTest.setVisible(true);
        newTest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

