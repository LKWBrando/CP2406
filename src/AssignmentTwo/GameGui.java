package AssignmentTwo;

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
    private JPanel displayTrump, northPanel, centerPanel, southPanel, displayCurrentInfo, titlePanel;
    private JLabel headerTitle, secondHeader, displayCurrentCardText, footer, displayPassedPlayers;
    private JButton pcThree, pcFour, pcFive, hardness, sGravity, cleavage, cAbundance, EValue, passTurn;
    private ArrayList<Card> cardDeck = new ArrayList<Card>();
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private ArrayList<Integer> finishedPlayerList = new ArrayList<Integer>(); //Arraylist to store finished players according to their ranking.
    private Map<Card, JButton> cardToButtonMap = new HashMap<Card, JButton>();
    private Map<JButton, Card> buttonToCardMap = new HashMap<JButton, Card>();
    private int playerCount; //Variable used to store the number of players in the game.
    private int currentPlayerIndex;//Variable used to indicate the arraylist index of the current player.
    private int playerPassCount = 0; //Variable used to count the number of players that has passed their turn.
    private int finishedPlayers = 0; //Variable used to count the number of finished players.
    private String currentTrump = null; //Variable based on current Trump category in play.
    private String currentTrumpName = null; //Variable based on name of the current card in play.
    private double currentDoubleValue = 0; //Variable based on the current double value of the Trump category of the card in play.
    private String currentStringValue = "Nothing!"; //Variable based on the String values of some of the Trump category of the card in play.
    private int totalCardCount = 60;
    private int dealCard;
    private double decidingTrumpValue;
    private Card tempCardObject;

    public GameGui() throws IOException{
        setTitle("SuperTrump Card Game");
        setSize(1500, 700);
        setLayout(new BorderLayout());

        northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.setPreferredSize(new Dimension(1500, 150));
        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(1500,400));
        southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.setPreferredSize(new Dimension(1500, 150));

        titlePanel = new JPanel(new BorderLayout());
        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        titlePanel.add(headerTitle, BorderLayout.CENTER);

        displayPassedPlayers = new JLabel("");

        northPanel.add(titlePanel);

        secondHeader = new JLabel("Please select the Number of Players", SwingConstants.CENTER);
        northPanel.add(secondHeader);

        add(northPanel, BorderLayout.NORTH);

        pcThree = new JButton("3 Players!");
        pcThree.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 3;
                initPlayParameters();
            }
        });
        pcFour = new JButton("4 Players!");
        pcFour.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 4;
                initPlayParameters();
            }
        });
        pcFive = new JButton("5 Players!");
        pcFive.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerCount = 5;
                initPlayParameters();
            }
        });
        centerPanel.add(pcThree);
        centerPanel.add(pcFour);
        centerPanel.add(pcFive);
        add(centerPanel, BorderLayout.CENTER);

        displayCurrentInfo = new JPanel();
        displayCurrentInfo.setPreferredSize(new Dimension(1000,75));

        displayCurrentCardText = new JLabel("", SwingConstants.CENTER);
        displayCurrentCardText.setPreferredSize(new Dimension(800,75));
        displayCurrentInfo.add(displayCurrentCardText);

        hardness = new JButton("Hardness");
        hardness.setPreferredSize(new Dimension(175,60));
        hardness.addActionListener(this);
        sGravity = new JButton("Specific Gravity");
        sGravity.setPreferredSize(new Dimension(175,60));
        sGravity.addActionListener(this);
        cleavage = new JButton("Cleavage");
        cleavage.setPreferredSize(new Dimension(175,60));
        cleavage.addActionListener(this);
        cAbundance = new JButton ("Crystal Abundance");
        cAbundance.setPreferredSize(new Dimension(175,60));
        cAbundance.addActionListener(this);
        EValue = new JButton("Economic Value");
        EValue.setPreferredSize(new Dimension(175,60));
        EValue.addActionListener(this);

        displayTrump = new JPanel(new GridLayout(1, 5));
        displayTrump.setPreferredSize(new Dimension(1000, 75));
        displayTrump.add(hardness);
        displayTrump.add(sGravity);
        displayTrump.add(cleavage);
        displayTrump.add(cAbundance);
        displayTrump.add(EValue);

        passTurn = new JButton("Pass");
        passTurn.setPreferredSize(new Dimension(150,60));
        passTurn.addActionListener(this);

        footer = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER);
        footer.setPreferredSize(new Dimension(1000, 75));

        southPanel.add(displayCurrentInfo);
        southPanel.add(footer);
        add(southPanel, BorderLayout.SOUTH);

        //Reading files
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

        ArrayList<String> imageFileLocation = new ArrayList<String>(); //Arraylist to store the string values of the file names
        File[] imageFiles = new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images").listFiles();
        for (File fileName : imageFiles) {
            imageFileLocation.add(fileName.getName());
        }

        ArrayList<JButton> cardImageList = new ArrayList<JButton>(); //Arraylist to store the JButtons containing the images of the card objects
        //Creating Jbuttons based on mineral images
        for (int i = 0; i < cardDeck.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File("C:\\Users\\user\\IdeaProjects\\CP2406\\a2_images\\" + imageFileLocation.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(130, 300, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            newButton.setPreferredSize(new Dimension(130,300));
            newButton.addActionListener(this);
            cardImageList.add(newButton);
        }
        //Mapping of the JButtons to the Card objects, and vice versa, allowing them to be used as keys to obtain the values of each other.
        for(int i = 0; i<cardDeck.size(); i++){
            cardToButtonMap.put(cardDeck.get(i), cardImageList.get(i));
            buttonToCardMap.put(cardImageList.get(i), cardDeck.get(i));
        }
    }

    //Method to remove all previous card object buttons in the centerpanel, and add the card object buttons from the current player's hand.
    private void displayPlayerHand(int x){
        centerPanel.removeAll();
        for (int i = 0 ; i < playerList.get(x).getPlayerHand().size(); i++){
            centerPanel.add(cardToButtonMap.get(playerList.get(x).getPlayerHand().get(i))).setPreferredSize(new Dimension(130,300));
            revalidate();
            repaint();
        }
    }

    //When a button is clicked
    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();

        //IF button clicked is an instance of a Card
        if(buttonToCardMap.get(clickedButton) instanceof MineralCard || buttonToCardMap.get(clickedButton) instanceof SuperTrumpCard){
            Card cardObject = buttonToCardMap.get(clickedButton);
            if (currentTrumpName == null) {
                if (cardObject instanceof MineralCard) {
                    footer.setText("You have selected: " + cardObject);

                    //As the player of the first card of the round, he/she is allowed to choose any of the categories in play
                    centerPanel.removeAll();
                    centerPanel.add(clickedButton);
                    displayCurrentInfo.remove(displayCurrentCardText);
                    displayCurrentInfo.add(displayTrump);
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
                    titlePanel.remove(displayPassedPlayers);
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
                            //runGame = false;
                        }
                    } else {
                        //Displaying the card that the player has played for the turn and removing it from the player hand.
                        secondHeader.setText("<html><div style='text-align: center;'>Player " + (currentPlayerIndex +1) + " has played the Trump Card: " + currentTrumpName + "<br>Player " + (currentPlayerIndex +1) + " gets another turn! All players get their turns back as well!</div></html>");
                        if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                            displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
                        } else {
                            displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
                        }
                        playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                        playerPassCount = 0;
                        //Allowing the player to get another turn, as per game mechanics of playing a Trump card.
                        //System.out.println("Player " + (currentPlayerIndex +1) + " has played a super trump card, starting a new round!");
                        checkWinCondition();
                        revalidate();
                        repaint();
                    }
                }
            }else{
                if (cardObject instanceof MineralCard) {
                    calTrumpValue(currentTrump, (MineralCard) cardObject);
                    if(decidingTrumpValue <= currentDoubleValue){
                        footer.setText("<html>The card you want to play has a lower value than the card in play!<br> Please select another card.</html>");
                    }
                    else{
                        currentTrumpName = tempCardObject.getMineralName();
                        changeTrumpValue(currentTrump, (MineralCard) cardObject);
                        secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played " + cardObject);
                        playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                        if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                            displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
                        } else {
                            displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
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
                    titlePanel.remove(displayPassedPlayers);
                    currentTrump = ((SuperTrumpCard) cardObject).getSuperTrumpCardCat(currentTrumpName, playerList.get(currentPlayerIndex));
                    currentDoubleValue = 0;
                    currentStringValue = "Nothing!";

                    secondHeader.setText("<html>Player " + (currentPlayerIndex +1) + " has played the Trump Card: " + currentTrumpName + "<br>Player " + (currentPlayerIndex +1) + " gets another turn! All players get their turns back as well!</html>");
                    if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                        displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
                    } else {
                        displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
                    }
                    playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                    playerPassCount = 0;
                    System.out.println("Player " + (currentPlayerIndex +1) + " has played a super trump card, starting a new round!");
                    checkWinCondition();
                    //if (!playerList.get(currentPlayerIndex).getPlayerHand().isEmpty()){ }
                }
            }
        }
        //ELSE IF button clicked is the Pass button
        else if (clickedButton.getText().equals("Pass")){
            Random randInt = new Random();
            if (totalCardCount != 0) {
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(currentPlayerIndex).drawCard(cardDeck.get(dealCard));
                cardDeck.remove(dealCard);
                totalCardCount -= 1;
                //System.out.println("<html>Player " + (currentPlayerIndex +1) + " has passed and drew a card!<br>The amount of cards left in the deck is: " + totalCardCount + "Please wait for your next turn!</html>");
            }
            //ELSE there are no more cards left in the deck...
            else {
                System.out.println("There are no more cards left in the deck! Please wait for your next turn.");
            }
            //Player turn status is changed to false as they forfeit their turn by drawing a card, as per game mechanics.
            playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
            playerPassCount += 1;
            //System.out.println(playerPassCount);
            currentPlayerIndex += 1;
            if (currentPlayerIndex == playerCount){
                currentPlayerIndex = 0;
            }
            titlePanel.remove(displayPassedPlayers);
            displayPassedPlayers.removeAll();
            titlePanel.add(displayPassedPlayers, BorderLayout.EAST);
            String printPlayerTurnStatus = "Players Passed:";
            for (Player aPlayer : playerList){
                if(!aPlayer.getPlayerTurnStatus()){
                    printPlayerTurnStatus = printPlayerTurnStatus + aPlayer.getPlayerIndex() + ", ";
                }
                displayPassedPlayers.setText(printPlayerTurnStatus);
            }
            runGame();
        }
        //ELSE the button clicked is one of the five selectable trump categories.
        else {
            currentTrump = (clickedButton.getText());

            displayCurrentInfo.remove(displayTrump);
            displayCurrentInfo.add(displayCurrentCardText);

            currentTrumpName = tempCardObject.getMineralName();
            changeTrumpValue(currentTrump, (MineralCard) tempCardObject);

            //Displaying the card that the player has played for the turn, then removing it from the player hand.
            secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played " + tempCardObject);
            playerList.get(currentPlayerIndex).getPlayerHand().remove(tempCardObject);

            if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
            } else {
                displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump cat is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
            }
            footer.setText("Select a card to play or draw a card and pass");
            checkWinCondition();
            revalidate();
            repaint();
        }
    }

    //Method to start the game by creating player objects based on number of players selected, and assigning card objects to the player's hand.
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
        footer.setText("Please select a card to play!");
        runGame();
    }

    //Method to check if player hand is empty after each card play, so as to determine if the player has won the game.
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
        else {
            if (currentTrumpName.equals("The Geophysicist") || currentTrumpName.equals("The Geologist") || currentTrumpName.equals("The Mineralogist") || currentTrumpName.equals("The Gemmologist") || currentTrumpName.equals("The Petrologist") || currentTrumpName.equals("The Miner")) {
                currentPlayerIndex -= 1;
            }
        }
        currentPlayerIndex += 1;
        if (currentPlayerIndex == playerCount){
            currentPlayerIndex = 0; }

        runGame();
    }

    //Recursive method to check if all but one player has passed, and to reset the round if that is the case.
    public void checkPlayerStatus(){
        if (playerList.get(currentPlayerIndex).getPlayerTurnStatus() && (playerPassCount == (playerCount - 1))) {
            secondHeader.setText("Every other player has passed! You are free to play any cards.");
            currentTrumpName = null;
            currentTrump = null;
            currentDoubleValue = 0;
            currentStringValue = "Nothing!";
            playerPassCount = 0;
            titlePanel.remove(displayPassedPlayers);
            for (Player aPlayer : playerList) {
                aPlayer.setPlayerTurnStatus(true);
            }
        }
        //else cycle through the players to determine if they are still playing or have passed.
        else{
            while(!playerList.get(currentPlayerIndex).getPlayerTurnStatus()) {
                currentPlayerIndex += 1;
                if(currentPlayerIndex == playerCount){
                    currentPlayerIndex = 0;
                }
                checkPlayerStatus();
            }
        }
        revalidate();
        repaint();
    }

    //Method used to reset the center panel of the game, and moves the game along by adding buttons according to the player's hand and status
    public void runGame(){
        checkPlayerStatus();
        centerPanel.removeAll();
        headerTitle.setText("Player " + (currentPlayerIndex +1) + " 's turn!");
        if(currentTrumpName == null || (currentDoubleValue == 0.0 && currentStringValue.equals("Nothing!"))){
            displayCurrentInfo.remove(passTurn);
        }else{
            displayCurrentInfo.add(passTurn);
        }
        displayPlayerHand(currentPlayerIndex);
        revalidate();
        repaint();
    }

    private void changeTrumpValue(String s, MineralCard playedCard){
        switch (s) {
            case "Hardness":
                currentDoubleValue = playedCard.getMineralHD();
                currentStringValue = null;
                break;
            case "Specific Gravity":
                currentDoubleValue = playedCard.getMineralSG();
                currentStringValue = null;
                break;
            case "Cleavage":
                currentDoubleValue = playedCard.getMineralCleavageValue(playedCard.getMineralCleavage());
                currentStringValue = playedCard.getMineralCleavage();
                break;
            case "Crystal Abundance":
                currentDoubleValue = playedCard.getMineralCaValue(playedCard.getMineralCA());
                currentStringValue = playedCard.getMineralCA();
                break;
            case "Economic Value":
                currentDoubleValue = playedCard.getMineralEvValue(playedCard.getMineralEcoValue());
                currentStringValue = playedCard.getMineralEcoValue();
                break;}
    }


    //Method to return a double value based on the card played.
    private void calTrumpValue(String currentTrump, MineralCard playedCard){
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
    }

    public static void main(String[] args) throws IOException {
        GameGui trumpGui = new GameGui();
        trumpGui.setVisible(true);
        trumpGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
