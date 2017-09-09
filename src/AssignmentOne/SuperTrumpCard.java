/*
The SuperTrumpCard class is the child class of the Card class.
Additional variables involved are:
1. cardDescription, used to store the string values of the SuperTrumpCard object
2.
 */

package AssignmentOne;

import java.util.Scanner;

public class SuperTrumpCard extends Card {
    private String cardDescription;
    private String cardTrumpCategory;

    public SuperTrumpCard(String mineralName, String cardDescription){
        super(mineralName);
        this.cardDescription = cardDescription;
    }

    public String getSuperTrumpCardCat(String currentTrumpName, int x,Player aPlayer, Card aCard){
        String cardTrumpCategory = null;
        int playerSelection;
        int selectUserInput;
        switch(currentTrumpName) {
            case "The Geophysicist":
                for (int i = 0; i< aPlayer.getPlayerHand().size(); i++){
                    if (aPlayer.getPlayerHand().get(x).getMineralName().equals("Magnetite")){
                        System.out.println("You have the Magnetite card in your hand, do you want to play it?\nEnter [1] for yes, [2] no");
                        Scanner userInput = new Scanner(System.in);
                        selectUserInput = userInput.nextInt();
                        if (selectUserInput == 1){
                            playerSelection = aPlayer.getPlayerHand().indexOf(aCard);
                            aPlayer.getPlayerHand().remove(playerSelection - 1);
                            cardTrumpCategory = "None!";
                        }
                        else{
                            System.out.println("You have chosen not to play the Magnetite card!");
                            cardTrumpCategory = "Specific Gravity";
                        }
                    }
                    else{
                        System.out.println("\nYou have played The Mineralogist! You have changed the current Trump Category to Cleavage. The play value has been reset!\"");
                    }
                }
                break;
            case "The Geologist":
                System.out.println("You have played The Geologist! You get to change the current Trump Category!\nChoose a Trump category to play!\n[1] : Hardness\n[2] : Specific Gravity\n[3] : Cleavage\n[4] : Crystal Abundance\n[5] : Economic Value\nThe play value has been reset!");
                Scanner userInput = new Scanner(System.in);
                selectUserInput = userInput.nextInt();
                switch (selectUserInput){
                    case 1:
                        cardTrumpCategory = "Hardness";
                        break;
                    case 2:
                        cardTrumpCategory = "Specific Gravity";
                        break;
                    case 3:
                        cardTrumpCategory = "Cleavage";
                        break;
                    case 4:
                        cardTrumpCategory = "Crystal Abundance";
                        break;
                    case 5:
                        cardTrumpCategory = "Economic Value";
                        break;
                }
                return cardTrumpCategory;

            case "The Mineralogist":
                System.out.println("You have played The Mineralogist! You have changed the current Trump Category to Cleavage. The play value has been reset!");
                cardTrumpCategory = "Cleavage";
                break;
            case "The Gemmologist":
                System.out.println("You have played the Gemmologist! You have changed the current Trump Category to Hardness. The play value has been reset!");
                cardTrumpCategory = "Hardness";
                break;
            case "The Petrologist":
                System.out.println("You have played the Petrologist! You have changed the current Trump Category to Crystal Abundance. The play value has been reset!");
                cardTrumpCategory = "Crystal Abundance";
                break;
            case "The Miner":
                System.out.println("You have played the Miner! You have changed the current Trump Category to Economic Value. The play value has been reset!");
                cardTrumpCategory = "Economic Value";
                break;
        }
        return cardTrumpCategory;
    }

    public String toString(){
        return super.toString() + "Special Ability: " +cardDescription;
    }



}
