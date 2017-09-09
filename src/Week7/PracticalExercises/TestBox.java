package Week7.PracticalExercises;

import java.util.Scanner;

public class TestBox {
    public static void main(String[] args) {
        int newUserInput = 0;

        System.out.println("Please enter the number of items in box A");
        Scanner userInput = new Scanner(System.in);
        newUserInput = userInput.nextInt();

        Box boxA = new Box(newUserInput);
        System.out.println("You have " + boxA.getNumberOfItems() + " number of items in box A");
try {
    while (true) {

        boxA.removeAnItem();
        System.out.println("Removed an Item!\nYou have " + boxA.getNumberOfItems() + " number of items in box A left");
    }
}catch (EmptyBoxException e) {
                System.out.println("There are no items left in the box!");

            }

    }
}
