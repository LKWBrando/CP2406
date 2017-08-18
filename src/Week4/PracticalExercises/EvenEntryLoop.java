package Week4.PracticalExercises;

import java.util.Scanner;

public class EvenEntryLoop {
    public static void main(String[] args) {
        int userInput;
        int modulusNumber;

        Scanner newUserInput = new Scanner(System.in);
        System.out.println("Please input an even number");
        userInput = newUserInput.nextInt();

        while (userInput != 999){
            modulusNumber = userInput % 2;
            if (modulusNumber == 0){
                System.out.println("Good Job! Enter another Even number.");}
            else
                System.out.println("Wrong entry! Please enter an Even number");
            Scanner nextUserInput = new Scanner(System.in);
            userInput = nextUserInput.nextInt();
        }
        System.out.println("Terminating program!");
    }
}