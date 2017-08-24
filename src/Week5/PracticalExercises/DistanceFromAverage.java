package Week5.PracticalExercises;

import java.util.Scanner;

public class DistanceFromAverage {
    public static void main(String[] args) {
        Double[] doubleList = new Double[20];
        Double userInput;
        Double userAverage;
        Double userSum = 0.0;
        Double valueDiff;
        int count = 0;

        Scanner newUserInput = new Scanner(System.in);
        System.out.println("Please enter a double value");
        userInput = newUserInput.nextDouble();

        while (userInput != 99999) {
            doubleList[count] = userInput;
            count += 1;
            Scanner nextUserInput = new Scanner(System.in);
            System.out.println("Please enter another value, or enter 99999 to exit");
            userInput = nextUserInput.nextDouble();}

        if (count == 0) {
            System.out.println("You have exited without entering a value");
        }
        else {
            for (int i = 0; i < count; i++) {
                userSum += doubleList[i];
            }
            userAverage = userSum / count;

            for (int i = 0; i<count; i++){
                valueDiff = Math.abs(userAverage - doubleList[i]);
                System.out.println("Your entered value is:" + doubleList[i] + " and the distance from the overall average is:" + valueDiff);
            }
        }
    }
}