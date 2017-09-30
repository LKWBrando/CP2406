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
    private JButton pcThree, pcFour, pcFive, hardness, sGravity, cleavage, cAbundance, EValue, passTurn, magWinCond1, magWinCond2;
    private ArrayList<Card> cardDeck = new ArrayList<Card>(); //Arraylist to store the Card objects used in the program
    private ArrayList<Player> playerList = new ArrayList<Player>(); //Arraylist to store the Player objects used in the program
    private ArrayList<Integer> finishedPlayerList = new ArrayList<Integer>(); //Arraylist to store finished players according to their ranking.
    private Map<Card, JButton> cardToButtonMap = new HashMap<Card, JButton>(); //Hashmap used to map the Card objects(Key) to the Jbutton objects containing the images
    private Map<JButton, Card> buttonToCardMap = new HashMap<JButton, Card>(); //Hashmap used to map the Jbutton objects(Key) to the Card objects
    private int playerCount; //Variable used to store the number of players in the game.
    private int currentPlayerIndex;//Variable used to indicate the arraylist index of the current player.
    private int playerPassCount = 0; //Variable used to count the number of players that has passed their turn.
    private int finishedPlayers = 0; //Variable used to count the number of finished players.
    private String currentTrump = null; //Variable based on current Trump category in play.
    private String currentTrumpName = null; //Variable based on name of the current card in play.
    private double currentDoubleValue = 0; //Variable based on the current double value of the Trump category of the card in play.
    private String currentStringValue = "Nothing!"; //Variable based on the String values of some of the Trump category of the card in play.
    private int totalCardCount = 60; //Total card count of the deck
    private int dealCard; //Variable used to store the randomised integer value used to randomly draw cards from the cardDeck.
    private double decidingTrumpValue; //Variable used to store the value of the successfully played card.
    private Card tempCardObject; //Object variable used to temporarily store the Card object properties to be used in several methods.

    //Constructor for the GameGui Class
    public GameGui() throws IOException{
        setTitle("SuperTrump Card Game");
        setSize(1500, 700);
        setLayout(new BorderLayout());

        //Setting up the three main JPanels
        northPanel = new JPanel(); //Used to display current information about the game
        northPanel.setLayout(new GridLayout(2,1));
        northPanel.setPreferredSize(new Dimension(1300, 150));
        centerPanel = new JPanel();//Used to display the various buttons containing the card images
        centerPanel.setPreferredSize(new Dimension(1300,350));
        southPanel = new JPanel(); //Used to display current information about the game
        southPanel.setLayout(new GridLayout(2, 1));
        southPanel.setPreferredSize(new Dimension(1300, 150));

        titlePanel = new JPanel(new BorderLayout()); //titlePanel will be used to hold the JLabels, headerTitle & displayPassedPlayers
        headerTitle = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER); //headerTitle used to display the current player's turn
        titlePanel.add(headerTitle, BorderLayout.CENTER);

        displayPassedPlayers = new JLabel(""); //JLabel used to display players who have passed, if there are any.
        secondHeader = new JLabel("Please select the Number of Players", SwingConstants.CENTER); //JLabel used to primarily display past played cards.

        northPanel.add(titlePanel);
        northPanel.add(secondHeader);
        add(northPanel, BorderLayout.NORTH);

        //pcThree, pcFour, pcFive JButtons are used to record user selection on the number of players for the game, and then execute the game.
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

        displayCurrentInfo = new JPanel(); //Used to display either the current play values of the game, or to display user selection on trump category buttons.
        displayCurrentInfo.setPreferredSize(new Dimension(1000,75));

        displayCurrentCardText = new JLabel("", SwingConstants.CENTER); //Primarily used to display current play values of the game
        displayCurrentCardText.setPreferredSize(new Dimension(800,75));
        displayCurrentInfo.add(displayCurrentCardText);

        //Following buttons are created for the five trump categories that the user can choose, under specific conditions.
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

        displayTrump = new JPanel(new GridLayout(1, 5)); //JPanel used to hold the five buttons.
        displayTrump.setPreferredSize(new Dimension(1000, 75));
        displayTrump.add(hardness);
        displayTrump.add(sGravity);
        displayTrump.add(cleavage);
        displayTrump.add(cAbundance);
        displayTrump.add(EValue);

        //MagWinCond1 and MagWinCond2 are buttons that are display when the player has the Geophysicist card and the Magnetite Card, allowing them to choose to win the game or continue playing
        magWinCond1 = new JButton("Yes");
        magWinCond1.setPreferredSize(new Dimension(175,60));
        magWinCond1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
                finishedPlayerList.add((currentPlayerIndex +1));
                finishedPlayers += 1;
                currentTrumpName = null;
                for (Player aPlayer : playerList) {
                    aPlayer.setPlayerTurnStatus(true);
                }
                playerPassCount = 0;
                currentPlayerIndex += 1;
                if (currentPlayerIndex == playerCount){
                    currentPlayerIndex = 0; }
                runGame();
            }
        });
        magWinCond2 = new JButton("No");
        magWinCond2.setPreferredSize(new Dimension(175,60));
        magWinCond2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTrump = "Specific Gravity";
                displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
                checkWinCondition();
            }
        });

        passTurn = new JButton("Pass"); //Button that allows the user to choose to pass his/her turn.
        passTurn.setPreferredSize(new Dimension(150,60));
        passTurn.addActionListener(this);

        footer = new JLabel("Welcome to the Mineral Supertrumps game!", SwingConstants.CENTER); //JLabel to display various information and prompts of the game.
        footer.setPreferredSize(new Dimension(1000, 75));

        southPanel.add(displayCurrentInfo);
        southPanel.add(footer);
        add(southPanel, BorderLayout.SOUTH);

        //Reading files and creating MineralCard objects.
        FileReader readFile = new FileReader(".\\card.txt");
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

        //Creating each of the SuperTrumpCard objects individually, and according to the images found in the folder.
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
        File[] imageFiles = new File(".\\a2_images").listFiles();
        for (File fileName : imageFiles) {
            imageFileLocation.add(fileName.getName());
        }

        ArrayList<JButton> cardImageList = new ArrayList<JButton>(); //Arraylist to store the JButtons containing the images of the card objects
        //Creating Jbuttons based on mineral images
        for (int i = 0; i < cardDeck.size(); i++) {
            BufferedImage newImage = ImageIO.read(new File(".\\a2_images\\" + imageFileLocation.get(i)));
            ImageIcon newIcon = new ImageIcon(newImage.getScaledInstance(150, 300, Image.SCALE_SMOOTH));
            JButton newButton = new JButton(newIcon);
            newButton.setPreferredSize(new Dimension(150,300));
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
            centerPanel.add(cardToButtonMap.get(playerList.get(x).getPlayerHand().get(i))).setPreferredSize(new Dimension(150,300));
            revalidate();
            repaint();
        }
    }

    //Actions performed when a button is clicked
    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();

        //IF Button clicked is an instance of a Card
        if(buttonToCardMap.get(clickedButton) instanceof MineralCard || buttonToCardMap.get(clickedButton) instanceof SuperTrumpCard){
            Card cardObject = buttonToCardMap.get(clickedButton);
            if (currentTrumpName == null) { //If the player is the first of the round...
                if (cardObject instanceof MineralCard) {
                    footer.setText("You have selected: " + cardObject);
                    centerPanel.removeAll();
                    centerPanel.add(clickedButton); //Showing user selection only
                    displayCurrentInfo.remove(displayCurrentCardText);
                    displayCurrentInfo.add(displayTrump); //Adding the Trump category buttons to allow user to choose
                    tempCardObject = cardObject; //Storing the cardObject temporarily
                    revalidate();
                    repaint();
                }

                else {  //ELSE cardObject is an instance of SuperTrumpCard
                    currentTrumpName = cardObject.getMineralName();
                    for (Player aPlayer : playerList) {
                        aPlayer.setPlayerTurnStatus(true);
                    }
                    titlePanel.remove(displayPassedPlayers);
                    tempCardObject = cardObject; //Storing the cardObject temporarily
                    getTrumpCardCat(); //Run method to determine actions for the trump card played.
                    secondHeader.setText("<html><div style='text-align: center;'>Player " + (currentPlayerIndex +1) + " has played the Trump Card: " + currentTrumpName + "<br>Player " + (currentPlayerIndex +1) + " gets another turn! All players get their turns back as well!</div></html>");
                    if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                        displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
                    } else {
                        displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
                    }
                    playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                    playerPassCount = 0;
                    if(!currentTrumpName.equals("The Geologist")){ //If the player does not have to choose the trump category...
                    checkWinCondition();
                    revalidate();
                    repaint();
                    }
                }
            }else{ //As the game progresses...
                if (cardObject instanceof MineralCard) {
                    calTrumpValue(currentTrump, (MineralCard) cardObject); //Calculate the value of the card that the player has selected
                    if(decidingTrumpValue <= currentDoubleValue){ //Ensuring that the player has to play a card that has a higher value
                        footer.setText("<html>The card you want to play has a lower value than the card in play!<br> Please select another card.</html>");
                    }
                    else{
                        tempCardObject = cardObject;
                        currentTrumpName =cardObject.getMineralName();
                        changeTrumpValue(currentTrump, (MineralCard) cardObject);
                        secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played " + cardObject);
                        playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                        if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                            displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
                        } else {
                            displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
                        }
                        footer.setText("Select a card to play or draw a card and pass");
                        checkWinCondition();
                        revalidate();
                        repaint();
                    }
                }
                else{ //ELSE cardObject is an instance of SuperTrumpCard...
                    currentTrumpName = cardObject.getMineralName();
                    for (Player aPlayer : playerList) {
                        aPlayer.setPlayerTurnStatus(true);
                    }
                    titlePanel.remove(displayPassedPlayers);
                    tempCardObject = cardObject;
                    getTrumpCardCat();//Run method to determine actions for the trump card played.
                    secondHeader.setText("<html>Player " + (currentPlayerIndex +1) + " has played the Trump Card: " + currentTrumpName + "<br>Player " + (currentPlayerIndex +1) + " gets another turn! All players get their turns back as well!</html>");
                    if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                        displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
                    } else {
                        displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
                    }
                    playerList.get(currentPlayerIndex).getPlayerHand().remove(cardObject);
                    playerPassCount = 0;
                    if(!currentTrumpName.equals("The Geologist")) { //If the player does not have to choose the trump category...
                        checkWinCondition();
                        revalidate();
                        repaint();
                    }
                }
            }
        }

        //ELSE IF button clicked is the Pass button
        else if (clickedButton.getText().equals("Pass")){
            Random randInt = new Random(); //Getting a random number to used in card drawing
            if (totalCardCount != 0) { //While there are still cards in the deck...
                dealCard = randInt.nextInt(totalCardCount);
                playerList.get(currentPlayerIndex).drawCard(cardDeck.get(dealCard));
                cardDeck.remove(dealCard);
                totalCardCount -= 1;
            }
            else { //ELSE there are no more cards left in the deck...
                System.out.println("There are no more cards left in the deck! Please wait for your next turn.");
            }
            //Player turn status is changed to false as they forfeit their turn by drawing a card, as per game mechanics.
            playerList.get(currentPlayerIndex).setPlayerTurnStatus(false);
            playerPassCount += 1;
            currentPlayerIndex += 1;
            if (currentPlayerIndex == playerCount){ //Looping mechanism for the player turns
                currentPlayerIndex = 0;
            }
            titlePanel.remove(displayPassedPlayers); //Resetting the text in the displayPassedPlayers JLabel
            displayPassedPlayers.removeAll(); //Resetting the position of displayPassedPlayers JLabel
            titlePanel.add(displayPassedPlayers, BorderLayout.EAST);
            String printPlayerTurnStatus = "Players Passed:";
            for (Player aPlayer : playerList){
                if(!aPlayer.getPlayerTurnStatus()){
                    printPlayerTurnStatus = printPlayerTurnStatus + aPlayer.getPlayerIndex() + ", ";
                }
                displayPassedPlayers.setText(printPlayerTurnStatus); //Displaying passed players of the round
            }
            runGame();
        }

        //ELSE the button clicked is one of the five selectable trump categories.
        else {
            currentTrump = (clickedButton.getText()); //Setting the current trump category accordingly
            displayCurrentInfo.remove(displayTrump);
            displayCurrentInfo.add(displayCurrentCardText); //Displaying play values of the game

            currentTrumpName = tempCardObject.getMineralName();

            if(tempCardObject instanceof MineralCard) { //IF the played card is an instance of a MineralCard, otherwise ignore
            changeTrumpValue(currentTrump, (MineralCard) tempCardObject); //Change values accordingly
            secondHeader.setText("Player " + (currentPlayerIndex +1) + " has played " + tempCardObject);//Displaying the card that the player has played for the turn
            playerList.get(currentPlayerIndex).getPlayerHand().remove(tempCardObject); //Removing it from the player hand.
            footer.setText("Select a card to play or draw a card and pass");
            }
            if (currentTrump.equals("Cleavage") || currentTrump.equals("Crystal Abundance") || currentTrump.equals("Economic Value")) {
                displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentStringValue + " </div></html>");
            } else {
                displayCurrentCardText.setText("<html><div style='text-align: center;'>The current Trump category is: " + currentTrump + "<br>The current value is: " + currentDoubleValue + " </div></html>");
            }
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
            displayCurrentCardText.setText("");
            playerList.get(currentPlayerIndex).setPlayerGameStatus(false);
            finishedPlayerList.add((currentPlayerIndex +1));
            finishedPlayers += 1;
            currentTrumpName = null;
            for (Player aPlayer : playerList) {
                aPlayer.setPlayerTurnStatus(true);
            }
            playerPassCount = 0;
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

    //Method to check if all but one player has finished the game, and to end it if so.
    public void checkFinishedPlayers(){
        if(finishedPlayers == (playerCount - 1)){
            headerTitle.setText("The game has finished!");
            secondHeader.setText("The overall winner is: " + finishedPlayerList.get(0));
            footer.setText("Thank you for playing!");
            String printFinishedPlayers = "The runner ups are:";
            for(int i = 1; i < finishedPlayerList.size(); i++){
                printFinishedPlayers = printFinishedPlayers + " Player: " + finishedPlayerList.get(i);
            }
            displayCurrentCardText.setText(printFinishedPlayers);
            displayCurrentInfo.remove(passTurn);
        }
        else if(!playerList.get(currentPlayerIndex).getPlayerGameStatus()){
            while(!playerList.get(currentPlayerIndex).getPlayerGameStatus()){
                currentPlayerIndex += 1;
                if (currentPlayerIndex == playerCount){
                    currentPlayerIndex = 0; }
            }
        }
    }

    //Recursive method to check if all but one player has passed, and to reset the round if that is the case.
    public void checkPlayerStatus(){
        if (playerList.get(currentPlayerIndex).getPlayerTurnStatus() && (playerPassCount == (playerCount - finishedPlayers - 1))) { //If every other player has passed, reset play values
            secondHeader.setText("Every other player has passed! You are free to play any cards.");
            displayCurrentCardText.setText("");
            footer.setText("Please select a card to play!");
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
        checkFinishedPlayers();
        centerPanel.removeAll();
        if(!headerTitle.getText().equals("The game has finished!")) {
            headerTitle.setText("Player " + (currentPlayerIndex + 1) + " 's turn!");
            if (currentTrumpName == null || (currentDoubleValue == 0.0 && currentStringValue.equals("Nothing!"))) {
                displayCurrentInfo.remove(passTurn);
            } else {
                displayCurrentInfo.add(passTurn);
            }
            displayPlayerHand(currentPlayerIndex);
            footer.setText("Select a card to play or draw a card and pass");
            revalidate();
            repaint();
        }
    }

    //Method that changes the play values accordingly to the played card.
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

    //Method run to determine actions to be taken when a trump card is played.
    private void getTrumpCardCat(){
        switch(currentTrumpName) {
            //Case for super trump card "The Geophysicist"
            case "The Geophysicist":
                //Checking the player hand for the specific Card object Magnetite
                for (int i = 0; i< playerList.get(currentPlayerIndex).getPlayerHand().size(); i++){
                    //If the player hand does contain the Card Magnetite...
                    if (playerList.get(currentPlayerIndex).getPlayerHand().get(i).getMineralName().equals("Magnetite")){

                        //IF the player chooses to play the trump card with the Magnetite card
                        secondHeader.setText("You have the Magnetite card in your hand, do you wish to play it and win the game?");
                        centerPanel.removeAll();
                        centerPanel.add(cardToButtonMap.get(tempCardObject));
                        displayCurrentInfo.removeAll();
                        displayCurrentInfo.add(magWinCond1);
                        displayCurrentInfo.add(magWinCond2);
                        revalidate();
                        repaint();
                        //ELSE the player chooses not to play the trump card with the Magnetite card
                    }
                    //ELSE the player hand does not contain the Card Magnetite...
                    else{
                        currentTrump = "Specific Gravity";
                    }
                }
                break;

            //Case for super trump card "The Geologist"
            case "The Geologist":
                secondHeader.setText("You have played The Geologist! You get to change the current Trump Category!");
                centerPanel.removeAll();
                centerPanel.add(cardToButtonMap.get(tempCardObject));
                displayCurrentInfo.removeAll();
                displayCurrentInfo.add(displayTrump);
                revalidate();
                repaint();
                break;

            //Case for the super trump card "The Mineralogist"
            case "The Mineralogist":
                currentTrump = "Cleavage";
                break;
            //Case for the super trump card "The Gemmologist"
            case "The Gemmologist":
                currentTrump = "Hardness";
                break;
            //Case for the super trump card "The Petrologist"
            case "The Petrologist":
                currentTrump = "Crystal Abundance";
                break;
            //Case for the super trump card "The Miner"
            case "The Miner":
                currentTrump = "Economic Value";
                break;
        }
        currentDoubleValue = 0.0;
        currentStringValue = "Nothing!";
    }

    //Starting the program
    public static void main(String[] args) throws IOException {
        GameGui trumpGui = new GameGui();
        trumpGui.setVisible(true);
        trumpGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
