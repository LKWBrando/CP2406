package Week7.PracticalExercises;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class example_1 {
    public static void main(String[] args){
        int userInput;
        String grade = null;
        System.out.println("Please enter an exam mark");

        while (true) {
            try {
                Scanner nextUserInput = new Scanner(System.in);
                userInput = nextUserInput.nextInt();


                grade = getGrade(userInput);
                System.out.println("The grade is : " + grade);
                break;

            }   catch(InvalidMarkException e){
                    System.out.println("Invalid number range!");
                }

            catch (InputMismatchException errorInput) {
                System.out.println("Input type is wrong! Please enter an int number!");
            }
        }
    }

    public static String getGrade(int userInput) throws InvalidMarkException {
        String grade;
        if (userInput <= 100 && userInput >= 50) {
            grade = "P";
            return grade;
        } else if (userInput < 50 && userInput >= 0) {
            grade = "F";
            return grade;
        } else {
            throw new InvalidMarkException("Invalid number range!");
        }
    }
}
