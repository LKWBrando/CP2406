package AssignmentTwo;

import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class GameGui extends JFrame implements ActionListener{
    private JPanel header, body,secondBody, displayTrump, displayInfo, northPanel, centerPanel, southPanel;
    private JLabel headerTitle, secondHeader, displayCurrentCard, footer;
    private JButton pcThree, pcFour, pcFive, hardness, sGravity, cleavage, cAbundance, EValue, passTurn;
    private ArrayList<JButton> imageList = new ArrayList<JButton>();
    private ArrayList<String> imagePathString = new ArrayList<String>();
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private Map<Card, JButton> cardToButtonMap = new HashMap<Card, JButton>();
    private Map<JButton, Card> buttonToCardMap = new HashMap<JButton, Card>();
    public int playerCount;
    ArrayList<Integer> finishedPlayerList = new ArrayList<Integer>(); //Arraylist to store finished players according to their ranking.
    int finishedPlayers = 0; //Variable used to count the number of finished players.
    int roundCount = 0; //Variable used to count the number of rounds
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
    double decidingTrumpValue;
    Card tempCardObject;

    public GameGui() throws IOException{
        setTitle("SuperTrump Card Game");
        setSize(1920, 1080);
        setLayout(new BorderLayout());

        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.setPreferredSize(new Dimension(1920, 100));
        centerPanel = new JPanel();
        //centerPanel.setPreferredSize(new Dimension(1920, 880));
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.setPreferredSize(new Dimension(1920, 100));

        //header = new JPanel();
        //header.setPreferredSize(new Dimension(1920, 80));
        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        northPanel.add(headerTitle);
        //add(header);

        //secondBody = new JPanel();
        //secondBody.setPreferredSize(new Dimension(1920, 100));
        secondHeader = new JLabel("Please select the Number of Players", SwingConstants.CENTER);
        northPanel.add(secondHeader);
        //add(secondBody);

        //body = new JPanel();
        //body.setPreferredSize(new Dimension(1920, 720));
        add(northPanel, BorderLayout.NORTH);

        pcThree = new JButton("3 Players!");
        pcThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 3;
                centerPanel.removeAll();
                revalidate();
                repaint();
                initPlayParameters();
            }
        });
        pcFour = new JButton("4 Players!");
        pcFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 4;
                centerPanel.removeAll();
                revalidate();
                repaint();
                initPlayParameters();
            }
        });
        pcFive = new JButton("5 Players!");
        pcFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 5;
                centerPanel.removeAll();
                revalidate();
                repaint();
                initPlayParameters();
            }
        });
        centerPanel.add(pcThree);
        centerPanel.add(pcFour);
        centerPanel.add(pcFive);
        add(centerPanel, BorderLayout.CENTER);

        displayCurrentCard = new JLabel();
        displayCurrentCard.setPreferredSize(new Dimension(1000,50));

        displayTrump = new JPanel(new GridLayout(1, 5));
        displayTrump.setPreferredSize(new Dimension(1000, 50));

        hardness = new JButton("Hardness");
        hardness.setPreferredSize(new Dimension(200,50));
        hardness.addActionListener(this);
        sGravity = new JButton("Specific Gravity");
        sGravity.setPreferredSize(new Dimension(200,50));
        sGravity.addActionListener(this);
        cleavage = new JButton("Cleavage");
        cleavage.setPreferredSize(new Dimension(200,50));
        cleavage.addActionListener(this);
        cAbundance = new JButton ("Crystal Abundance");
        cAbundance.setPreferredSize(new Dimension(200,50));
        cAbundance.addActionListener(this);
        EValue = new JButton("Economic Value");
        EValue.setPreferredSize(new Dimension(200,50));
        EValue.addActionListener(this);

        displayTrump.add(hardness);
        displayTrump.add(sGravity);
        displayTrump.add(cleavage);
        displayTrump.add(cAbundance);
        displayTrump.add(EValue);

        //displayInfo = new JPanel();
        southPanel.add(displayCurrentCard);

        footer = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        footer.setPreferredSize(new Dimension(1920, 50));

        southPanel.add(footer);
        add(southPanel, BorderLayout.SOUTH);

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
                    System.out.println("Player " + (currentPlayerIndex +1) + " has passed and drew a card! \nThe amount of cards left in the deck is: " + totalCardCount + "\nPlease wait for your next turn!");
                }
                //ELSE there are no more cards left in the deck...
                else {
                    System.out.println("There are no more cards left in the deck! Please wait for your next turn.");
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
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(150, 500, Image.SCALE_SMOOTH));
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

    public void setTrumpCategory(String trumpCategory){
        this.currentTrump = trumpCategory;
    }
    //Display
    public void displayPlayerHand(int x){
        centerPanel.removeAll();
        for (int i = 0 ; i < playerList.get(x).getPlayerHand().size(); i++){
            centerPanel.add(cardToButtonMap.get(playerList.get(x).getPlayerHand().get(i))).setPreferredSize(new Dimension(150,400));
            revalidate();
            repaint();
        }
    }
    //When a button is clicked
    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();

        if(buttonToCardMap.get(clickedButton) instanceof MineralCard || buttonToCardMap.get(clickedButton) instanceof SuperTrumpCard){
            Card cardObject = buttonToCardMap.get(clickedButton);
            if (currentTrumpName == null ||(playerPassCount == (playerCount - 1))) {
                if (cardObject instanceof MineralCard) {
                    footer.setText("You have selected: " + cardObject);

                    //As the player of the first card of the round, he/she is allowed to choose any of the categories in play
                    centerPanel.removeAll();
                    centerPanel.add(clickedButton);
                    southPanel.removeAll();
                    southPanel.add(displayTrump);
                    southPanel.add(footer);
                    revalidate();
                    repaint();
                    tempCardObject = cardObject;
                }

                //ELSE Trump card is played...
                else {
                    currentTrumpName = cardObject.getMineralName();
                    for (Player aPlayer : playerList) {
                        aPlayer.setPlayerTurnStatus(true);
                    }
                    currentTrump = ((SuperTrumpCard) cardObject).getSuperTrumpCardCat(currentTrumpName, playerList.get(currentPlayerIndex));
                    currentDoubleValue = 0;
                    currentStringValue = "Nothing!";

                    if (currentTrump.equals("None")) {
                        playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                        secondHeader.setText("Player " + (currentPlayerIndex +1) + " has finished the game by playing the SuperTrump card 'The Geophysicist' with the Magnetite card!");
                        finishedPlayerList.add((currentPlayerIndex +1));
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
                        secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played the Trump Card: " + currentTrumpName + "Player " + (currentPlayerIndex +1) + " gets another turn! All players get their turns back as well!");
                        if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                            displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentStringValue);
                        } else {
                            displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentDoubleValue);
                        }
                        playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                        playerPassCount = 0;
                        //Allowing the player to get another turn, as per game mechanics of playing a Trump card.
                        System.out.println("Player " + (currentPlayerIndex +1) + " has played a super trump card, starting a new round!");
                        roundCount += 1;
                        currentPlayerIndex -= 1;
                        checkWinCondition();
                    }
                }
            }else{
                if (cardObject instanceof MineralCard) {
                    decidingTrumpValue = calTrumpValue(currentTrump, (MineralCard) cardObject);
                    if(decidingTrumpValue <= currentDoubleValue){
                        footer.setText("The card you want to play has a lower value than the card in play! Please select another card.");
                    }
                    else{
                        currentTrumpName = tempCardObject.getMineralName();
                        currentDoubleValue = changeTrumpValue(currentTrump, (MineralCard) cardObject);
                        currentStringValue = changeTrumpStringValue(currentTrump, (MineralCard) cardObject);
                        secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played " + cardObject);
                        playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                        if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                            displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentStringValue);
                        } else {
                            displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentDoubleValue);
                        }
                        footer.setText("Select a card to play or draw a card and pass");
                        checkWinCondition();
                        revalidate();
                        repaint();
                    }
                }
                else{
                    currentTrumpName = cardObject.getMineralName();
                    for (Player aPlayer : playerList) {
                        aPlayer.setPlayerTurnStatus(true);
                    }
                    currentTrump = ((SuperTrumpCard) cardObject).getSuperTrumpCardCat(currentTrumpName, playerList.get(currentPlayerIndex));
                    currentDoubleValue = 0;
                    currentStringValue = "Nothing!";

                    secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played the Trump Card: " + currentTrumpName + "\nPlayer " + (currentPlayerIndex +1) + " gets another turn! All players get their turns back as well!");
                    if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                        displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentStringValue);
                    } else {
                        displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentDoubleValue);
                    }
                    playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                    playerPassCount = 0;
                    playerTurnCount -= 1;
                    System.out.println("\nPlayer " + (currentPlayerIndex +1) + " has played a super trump card, starting a new round!");
                    roundCount += 1;
                    currentPlayerIndex -= 1;
                    checkWinCondition();
                    if (!playerList.get(currentPlayerIndex).getPlayerHand().isEmpty()){

                    }
                }
            }
        }
        if((clickedButton.getText().equals("Hardness")) || clickedButton.getText().equals("Specific Gravity") || clickedButton.getText().equals("Cleavage") || clickedButton.getText().equals("Crystal Abundance") || clickedButton.getText().equals("Economic Value")){
            System.out.println("Test");
            setTrumpCategory(clickedButton.getText());

            southPanel.removeAll();
            southPanel.add(displayCurrentCard);
            southPanel.add(footer);

            currentTrumpName = tempCardObject.getMineralName();
            currentDoubleValue = changeTrumpValue(currentTrump, (MineralCard) tempCardObject);
            currentStringValue = changeTrumpStringValue(currentTrump, (MineralCard) tempCardObject);

            //Displaying the card that the player has played for the turn, then removing it from the player hand.
            secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played " + tempCardObject);
            playerList.get(currentPlayerIndex).getPlayerHand().remove(tempCardObject);

            if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentStringValue);
            } else {
                displayCurrentCard.setText("The current Trump cat is: " + currentTrump + " and the value is: " + currentDoubleValue);
            }
            footer.setText("Select a card to play or draw a card and pass");
            checkWinCondition();
            revalidate();
            repaint();
        }
    }

    public void checkWinCondition(){
        if (playerList.get(currentPlayerIndex).getPlayerHand().isEmpty()){
            secondHeader.setText("Player " + (currentPlayerIndex +1) + " has finished the game! The game is restarting, remaining players get ready!");
            playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
            finishedPlayerList.add((currentPlayerIndex +1));
            finishedPlayers += 1;
            currentTrumpName = null;
            for (Player aPlayer : playerList) {
                aPlayer.setPlayerTurnStatus(true);
            }
            playerPassCount = 0;
            playerCount -= 1;
        }
        currentPlayerIndex += 1;
        if (currentPlayerIndex == playerCount){
            currentPlayerIndex = 0;
        }
        runGame(currentPlayerIndex);
    }

    public void initPlayParameters(){
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
        secondHeader.setText("You get to play the first card!");
        footer.setText("");
        runGame(currentPlayerIndex);
    }

    public void runGame(int currentPlayerIndex){
        if(playerList.get(currentPlayerIndex).getPlayerGameStatus()){
        headerTitle.setText("Player " + (currentPlayerIndex +1) + " 's turn!");
        displayPlayerHand(currentPlayerIndex);
        revalidate();
        repaint();
        }
        else{currentPlayerIndex += 1;}
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
