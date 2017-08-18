package Week4.PracticalExercises;

import java.util.Scanner;

public class CondoSales2 {
    public static void main(String[] args) {
        int userInput;
        int condoPrice;
        int garageInput;
        Scanner newUserInput = new Scanner(System.in);
        System.out.println("Please enter 1 for Park view, 2 for golf course view, 3 for lake view");
        userInput = newUserInput.nextInt();

        switch (userInput) {
            case 1:
                condoPrice = 150000;
                System.out.println("Park view condos are $" + condoPrice);
                break;
            case 2:
                condoPrice = 170000;
                System.out.println("Golf Course view condos are $" + condoPrice);
                break;
            case 3:
                condoPrice = 210000;
                System.out.println("Lake view condos are $" + condoPrice);
                break;
            default:
                condoPrice = 0;
                System.out.println("Invalid number");
        }

        Scanner newGarageInput = new Scanner(System.in);
        System.out.println("Please input 1 for garage and 2 for parking space");
        garageInput = newGarageInput.nextInt();

        if (userInput >0 && userInput < 4)
            if (garageInput == 1)
                condoPrice += 5000;
                System.out.println("The total price of the condo is $" + condoPrice);
    }
}

