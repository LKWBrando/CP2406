package AssignmentTwo;


import com.sun.deploy.panel.JavaPanel;

import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class GameGui extends JFrame implements ActionListener{
    private JPanel header, body,secondBody, displayTrump, displayInfo;
    private JLabel headerTitle, secondHeader, displayCurrentCard, footer;
    private JButton pcThree, pcFour, pcFive, hardness, sGravity, cleavage, cAbundance, EValue, passTurn;
    private ArrayList<JButton> imageList = new ArrayList<JButton>();
    private ArrayList<String> imagePathString = new ArrayList<String>();
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Map<Card, JButton> cardToButtonMap = new HashMap<Card, JButton>();
    private Map<JButton, Card> buttonToCardMap = new HashMap<JButton, Card>();
    public int playerCount;
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
    Boolean runGame = true;
    int playerTurnCount = 0;
    int totalCardCount = 60;
    int dealCard;
    Boolean trumpCatButtonPressed = false;
    Card placeHolder;

    public GameGui() throws IOException{
        setTitle("SuperTrump Card Game");
        setSize(1920, 1080);
        setLayout(new GridLayout(5, 1));

        header = new JPanel();
        header.setPreferredSize(new Dimension(1920, 80));
        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        header.add(headerTitle);
        add(header);

        secondBody = new JPanel();
        secondBody.setPreferredSize(new Dimension(1920, 100));
        secondHeader = new JLabel("Please select the Number of Players", SwingConstants.CENTER);
        secondBody.add(secondHeader);
        add(secondBody);

        body = new JPanel();
        body.setPreferredSize(new Dimension(1920, 720));


        pcThree = new JButton("3 Players!");
        pcThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerCount(3);
                body.removeAll();
                revalidate();
                repaint();
                runGame();
            }
        });
        pcFour = new JButton("4 Players!");
        pcFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerCount(4);
                body.removeAll();
                revalidate();
                repaint();
                runGame();
            }
        });
        pcFive = new JButton("5 Players!");
        pcFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPlayerCount(5);
                body.removeAll();
                revalidate();
                repaint();
                runGame();
            }
        });
        body.add(pcThree);
        body.add(pcFour);
        body.add(pcFive);
        add(body);

        displayCurrentCard = new JLabel();
        displayCurrentCard.setPreferredSize(new Dimension(1000,50));

        displayTrump = new JPanel(new GridLayout(1, 5));
        displayTrump.setPreferredSize(new Dimension(1000, 50));
        hardness = new JButton("Hardness");
        hardness.setPreferredSize(new Dimension(200,50));

        sGravity = new JButton("Specific Gravity");
        sGravity.setPreferredSize(new Dimension(200,50));

        cleavage = new JButton("Cleavage");
        cleavage.setPreferredSize(new Dimension(200,50));

        cAbundance = new JButton ("Crystal Abundance");
        cAbundance.setPreferredSize(new Dimension(200,50));

        EValue = new JButton("Economic Value");
        EValue.setPreferredSize(new Dimension(200,50));

        displayTrump.add(hardness);
        displayTrump.add(sGravity);
        displayTrump.add(cleavage);
        displayTrump.add(cAbundance);
        displayTrump.add(EValue);

        displayInfo = new JPanel();
        add(displayInfo);

        footer = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        footer.setPreferredSize(new Dimension(1920, 80));
        add(footer);

        passTurn = new JButton("Pass your turn and draw a card.");
        passTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random randInt = new Random();
                if (totalCardCount != 0) {
                    dealCard = randInt.nextInt(totalCardCount);
                    playerList.get(currentPlayerIndex).drawCard(cardDeck.get(dealCard));
                    cardDeck.remove(dealCard);
                    totalCardCount -= 1;
                    System.out.println("\nPlayer " + currentPlayer + " has passed and drew a card! \nThe amount of cards left in the deck is: " + totalCardCount + "\nPlease wait for your next turn!");
                }
                //ELSE there are no more cards left in the deck...
                else {
                    System.out.println("\nThere are no more cards left in the deck! Please wait for your next turn.");
                }
                //Player turn status is changed to false as they forfeit their turn by drawing a card, as per game mechanics.
                playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
                playerPassCount += 1;
            }
        });

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

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public void setTrumpCategory(String trumpCategory){
        this.currentTrump = trumpCategory;
    }

    public void displayPlayerHand(int x){
        body.removeAll();
        for (int i = 0 ; i < playerList.get(x).getPlayerHand().size(); i++){
            body.add(cardToButtonMap.get(playerList.get(x).getPlayerHand().get(i))).setPreferredSize(new Dimension(150,400));
            revalidate();
            repaint();
        }
    }

    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();

        if(!clickedButton.getText().equals("Hardness") || !clickedButton.getText().equals("Specific Gravity") || !clickedButton.getText().equals("Cleavage") || !clickedButton.getText().equals("Crystal Abundance") || !clickedButton.getText().equals("Economic Value")){
            Card cardObject = buttonToCardMap.get(clickedButton);
            if (currentTrumpName == null ||(playerPassCount == (playerCount - 1))) {
                if (cardObject instanceof MineralCard) {
                    footer.setText("You have selected: " + cardObject);

                    //As the player of the first card of the round, he/she is allowed to choose any of the categories in play
                    body.removeAll();
                    body.add(clickedButton);
                    displayInfo.add(displayTrump);
                    revalidate();
                    repaint();
                    placeHolder = cardObject;
                }

                //ELSE Trump card is played...
                else {
                    currentTrumpName = cardObject.getMineralName();
                    //Resetting all player turns, according to the game mechanics of playing a Trump card.
                    for (Player aPlayer : playerList) {
                        aPlayer.setPlayerTurnStatus(true);
                    }
                    //Changing the Trump category in play according to the SuperTrump card properties.
                    currentTrump = ((SuperTrumpCard) cardObject).getSuperTrumpCardCat(currentTrumpName, playerList.get(currentPlayerIndex));
                    currentDoubleValue = 0;
                    currentStringValue = "Nothing!";
                    //The super trump card "The Geophysicist" will return a value of String "None" to the variable currentTrump if the user plays it with the Magnetite card.
                    if (currentTrump.equals("None")) {
                        playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                        System.out.println("Player " + currentPlayer + " has finished the game by playing the SuperTrump card 'The Geophysicist' with the Magnetite card!");
                        finishedPlayerList.add(currentPlayer);
                        finishedPlayers += 1;
                        currentTrumpName = null;
                        playerPassCount = 0;
                        playerCount -= 1;

                        //IF statement used to determine how many players are left. One player remaining ends the game loop.
                        if (finishedPlayers > playerCount) {
                            runGame = false;
                        }
                    } else {
                        //Displaying the card that the player has played for the turn and removing it from the player hand.
                        System.out.println("\nPlayer " + currentPlayer + " has played the Trump Card: " + currentTrumpName + "\n\nPlayer " + currentPlayer + " gets another turn! All players get their turns back as well!");
                        playerList.get(currentPlayerIndex).getPlayerHand().remove(playerSelection - 1);
                        playerPassCount = 0;
                        //Allowing the player to get another turn, as per game mechanics of playing a Trump card.
                        playerTurnCount -= 1;
                    }
                    System.out.println("\nPlayer " + currentPlayer + " has played a super trump card, starting a new round!");
                    roundCount += 1;
                }
            }
        }
        else{
            switch (clickedButton.getText()){
                case "Hardness":
                    setTrumpCategory("Hardness");
                    break;
                case "Specific Gravity":
                    setTrumpCategory("Specific Gravity");
                    break;
                case "Cleavage":
                    setTrumpCategory("Cleavage");
                    break;
                case "Crystal Abundance":
                    setTrumpCategory("Crystal Abundance");
                    break;
                case "Economic Value":
                    setTrumpCategory("Economic Value");
                    break;
            }

            displayInfo.remove(displayTrump);
            displayInfo.add(displayCurrentCard);
   
            currentTrumpName = placeHolder.getMineralName();
            currentDoubleValue = changeTrumpValue(currentTrump, (MineralCard) placeHolder);
            currentStringValue = changeTrumpStringValue(currentTrump, (MineralCard) placeHolder);

            //Displaying the card that the player has played for the turn, then removing it from the player hand.
            secondHeader.setText("Player " + currentPlayer + " has played " + placeHolder);
            playerList.get(currentPlayerIndex).getPlayerHand().remove(placeHolder);
            currentPlayerIndex += 1;
            trumpCatButtonPressed = false;
            if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentStringValue);
            } else {
                displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentDoubleValue);
            }
            revalidate();
            repaint();
            footer.setText("Select a card to play or draw a card and pass");
        }
    }

    public void runGame(){

        int playerListCount = 1;
        for (int x = 1; x <= playerCount; x++) {
            Player newPlayer = new Player(playerListCount);
            playerListCount += 1;
            playerList.add(newPlayer);
        }
        Random randInt = new Random();
        for (int x = 0; x < playerCount; x++) {
            for (int y = 0; y < 8; y++) {
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(x).drawCard(cardDeck.get(dealCard));
                cardDeck.remove(dealCard);
                totalCardCount -= 1;
            }
        }
            displayPlayerHand(0);
            revalidate();
            repaint();

    }

    /*
    Method to return value of the trump category of the card the user has played, using double values.
    For trump categories that use String values, the values are passed through the MineralCard Class methods of getMineralCleavageValue, getMineralCaValue, and getMineralEvValue.
    These methods return a double value based on the given ranking of the String values, which is then assigned to the variable currentTrumpValue in this method, to be used as comparison values in the main.
    */
    private static double changeTrumpValue(String s, MineralCard playedCard){
        double currentTrumpValue = 0.0;
        switch (s) {
            case "Hardness":
                currentTrumpValue = playedCard.getMineralHD();
                break;
            case "Specific Gravity":
                currentTrumpValue = playedCard.getMineralSG();
                break;
            case "Cleavage":
                currentTrumpValue = playedCard.getMineralCleavageValue(playedCard.getMineralCleavage());
                break;
            case "Crystal Abundance":
                currentTrumpValue = playedCard.getMineralCaValue(playedCard.getMineralCA());
                break;
            case "Economic Value":
                currentTrumpValue = playedCard.getMineralEvValue(playedCard.getMineralEcoValue());
                break;}
        return currentTrumpValue;
    }

    /*
    Method to return the value of the trump category of the card the user has played, using String values.
    For trump categories that use double values, the method will return null values for the variable currentStringValue.
     */
    private static String changeTrumpStringValue(String playCategory, MineralCard playedCard){
        String currentStringValue = null;
        switch (playCategory) {
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
                decidingTrumpValue = playedCard.getMineralEvValue(playedCard.getMineralEcoValue());
        }
        return decidingTrumpValue;
    }

    public static void main(String[] args) throws IOException {
        GameGui trumpGui = new GameGui();
        trumpGui.setVisible(true);
        trumpGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
