package Week4.PracticalExercises;
import java.util.Scanner;

public class CondoSales {
    public static void main(String[] args) {
        int userInput;
        int condoPrice;
        Scanner newUserInput = new Scanner(System.in);
        System.out.println("Please enter 1 for Park view, 2 for golf course view, 3 for lake view");
        userInput = newUserInput.nextInt();

        /*if (userInput > 3 || userInput <0)
            while (userInput > 3 || userInput < 0){
                condoPrice = 0;
                Scanner nextUserInput = new Scanner(System.in);
                System.out.println("Please enter 1 for Park view, 2 for golf course view, 3 for lake view");
                userInput = nextUserInput.nextInt();
            }
        else*/
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
        }
    }
