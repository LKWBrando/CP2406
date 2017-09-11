/*
The SuperTrumpCard class is the child class of the Card class.
Additional variables involved are:
1. cardDescription, used to store the string values of the SuperTrumpCard object
2. cardTrumpCategory
 */

package AssignmentOne;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SuperTrumpCard extends Card {
    private String cardDescription;
    private String cardTrumpCategory;

    public SuperTrumpCard(String mineralName, String cardDescription){
        super(mineralName);
        this.cardDescription = cardDescription;
    }

    public String getCardTrumpCategory() {
        return cardTrumpCategory;
    }

    public String getSuperTrumpCardCat(String currentTrumpName, int currentPlayerIndex, Player aPlayer, Card aCard){
        String trumpCardCategory = null;
        int playerSelection;
        int selectUserInput;
        switch(currentTrumpName) {
            case "The Geophysicist":
                for (int i = 0; i< aPlayer.getPlayerHand().size(); i++){
                    if (aPlayer.getPlayerHand().get(currentPlayerIndex).getMineralName().equals("Magnetite")){
                        System.out.println("You have the Magnetite card in your hand, do you want to play it?\nEnter [1] for yes, [2] no");
                        Scanner userInput = new Scanner(System.in);
                        selectUserInput = userInput.nextInt();
                        if (selectUserInput == 1){
                            playerSelection = aPlayer.getPlayerHand().indexOf(aCard);
                            aPlayer.getPlayerHand().remove(playerSelection - 1);
                            trumpCardCategory = "None";
                        }
                        else{
                            System.out.println("You have chosen not to play the Magnetite card!");
                            trumpCardCategory = "Specific Gravity";
                        }
                    }
                    else{
                        System.out.println("\nYou have played The Mineralogist! You have changed the current Trump Category to Cleavage. The play value has been reset!\"");
                    }
                }
                break;
            case "The Geologist":
                System.out.println("You have played The Geologist! You get to change the current Trump Category!");
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

            case "The Mineralogist":
                System.out.println("You have played The Mineralogist! You have changed the current Trump Category to Cleavage. The play value has been reset!");
                trumpCardCategory = "Cleavage";
                break;
            case "The Gemmologist":
                System.out.println("You have played the Gemmologist! You have changed the current Trump Category to Hardness. The play value has been reset!");
                trumpCardCategory = "Hardness";
                break;
            case "The Petrologist":
                System.out.println("You have played the Petrologist! You have changed the current Trump Category to Crystal Abundance. The play value has been reset!");
                trumpCardCategory = "Crystal Abundance";
                break;
            case "The Miner":
                System.out.println("You have played the Miner! You have changed the current Trump Category to Economic Value. The play value has been reset!");
                trumpCardCategory = "Economic Value";
                break;
        }
        return trumpCardCategory;
    }

    public String toString(){
        return super.toString() + "Special Ability: " +cardDescription;
    }



}
