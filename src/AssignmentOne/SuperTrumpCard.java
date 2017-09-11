package AssignmentOne;

import java.util.InputMismatchException;
import java.util.Scanner;

//The SuperTrumpCard class is the sub class of the Card class, used to create objects that have the base variabe of mineralName
public class SuperTrumpCard extends Card {

    //Additional variables involved are:
    //1. cardDescription, used to store the string values of the SuperTrumpCard object
    private String cardDescription;
    //2. cardTrumpCategory, used to store the string values of the trump category based off the selected super trump card
    private String cardTrumpCategory;

    //Constructor for the SuperTrumpCard Class
    public SuperTrumpCard(String mineralName, String cardDescription){
        super(mineralName);
        this.cardDescription = cardDescription;
    }

    /*Method getSuperTrumpCardCat passes the String currentTrumpName, which is the name of the card played, or in play,
      integer index of the current player, current Player object, as well as current Card object.
      It returns the trump category associated with the respective trump card, */
    public String getSuperTrumpCardCat(String currentTrumpName, int currentPlayerIndex, Player aPlayer, Card aCard){
        String trumpCardCategory = null;
        int selectUserInput; //Variable that stores the user input integer value
        switch(currentTrumpName) {

            //Case for super trump card "The Geophysicist"
            case "The Geophysicist":
                //Checking the player hand for the specific Card object Magnetite
                for (int i = 0; i< aPlayer.getPlayerHand().size(); i++){
                    //If the player hand does contain the Card Magnetite...
                    if (aPlayer.getPlayerHand().get(currentPlayerIndex).getMineralName().equals("Magnetite")){
                        System.out.println("You have the Magnetite card in your hand, do you want to play it?\nEnter [1] for yes, [2] no");
                        Scanner userInput = new Scanner(System.in);
                        selectUserInput = userInput.nextInt();
                        //IF the player chooses to play the trump card with the Magnetite card
                        if (selectUserInput == 1){
                            int playerSelection; //Variable used to obtain and store the index value of the trump card
                            playerSelection = aPlayer.getPlayerHand().indexOf(aCard);
                            aPlayer.getPlayerHand().remove(playerSelection - 1);
                            trumpCardCategory = "None";
                        }
                        //ELSE the player chooses not to play the trump card with the Magnetite card
                        else{
                            System.out.println("You have chosen not to play the Magnetite card!\nYou have played The Mineralogist! You have changed the current Trump Category to Specific Gravity. The play value has been reset!");
                            trumpCardCategory = "Specific Gravity";
                        }
                    }
                    //ELSE the player hand does not contain the Card Magnetite...
                    else{
                        System.out.println("You do not have the Magnetite Card!\nYou have played The Mineralogist! You have changed the current Trump Category to Specific Gravity. The play value has been reset!");
                        trumpCardCategory = "Specific Gravity";
                    }
                }
                break;
            //Case for super trump card "The Geologist"
            case "The Geologist":
                System.out.println("You have played The Geologist! You get to change the current Trump Category!");
                //Asking for user input with error checking for the desired trump category to play
                while (true){
                    try{
                        System.out.println("\nChoose a Trump category to play!\n[1] : Hardness\n[2] : Specific Gravity\n[3] : Cleavage\n[4] : Crystal Abundance\n[5] : Economic Value\nThe play value has been reset!");
                        Scanner userInput = new Scanner(System.in);
                        selectUserInput = userInput.nextInt();
                        while (selectUserInput < 1 || selectUserInput > 5){
                            System.out.println("");
                            selectUserInput = userInput.nextInt();
                        }
                        break;
                    }
                    catch (InputMismatchException error){
                        System.out.println("You have entered the wrong data type!");
                    }
                }

                //Based on user input, the corresponding trump category is returned
                switch (selectUserInput){
                    case 1:
                        trumpCardCategory = "Hardness";
                        break;
                    case 2:
                        trumpCardCategory = "Specific Gravity";
                        break;
                    case 3:
                        trumpCardCategory = "Cleavage";
                        break;
                    case 4:
                        trumpCardCategory = "Crystal Abundance";
                        break;
                    case 5:
                        trumpCardCategory = "Economic Value";
                        break;
                }
                return trumpCardCategory;

            //Case for the super trump card "The Mineralogist"
            case "The Mineralogist":
                System.out.println("You have played The Mineralogist! You have changed the current Trump Category to Cleavage. The play value has been reset!");
                trumpCardCategory = "Cleavage";
                break;
            //Case for the super trump card "The Gemmologist"
            case "The Gemmologist":
                System.out.println("You have played the Gemmologist! You have changed the current Trump Category to Hardness. The play value has been reset!");
                trumpCardCategory = "Hardness";
                break;
            //Case for the super trump card "The Petrologist"
            case "The Petrologist":
                System.out.println("You have played the Petrologist! You have changed the current Trump Category to Crystal Abundance. The play value has been reset!");
                trumpCardCategory = "Crystal Abundance";
                break;
            //Case for the super trump card "The Miner"
            case "The Miner":
                System.out.println("You have played the Miner! You have changed the current Trump Category to Economic Value. The play value has been reset!");
                trumpCardCategory = "Economic Value";
                break;
        }
        return trumpCardCategory;
    }

    //Overwritten toString() to display the super trump card name and description
    public String toString(){
        return super.toString() + "Special Ability: " +cardDescription;
    }
}