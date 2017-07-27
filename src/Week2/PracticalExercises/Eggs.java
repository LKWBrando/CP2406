package Week2.PracticalExercises;
import java.util.Scanner;

public class Eggs {
    public static void main(String[] args) {
        double singleEggCost;
        double dozenEggCost;
        int inputEgg;
        int singleEggCount;
        int dozenEggCount;
        double totalCost;
        singleEggCost = 0.45;
        dozenEggCost = 3.25;
        Scanner inputEggs = new Scanner(System.in);
        System.out.println("Input the number of eggs you want to order");
        inputEgg = inputEggs.nextInt();
        singleEggCount = inputEgg%12;
        singleEggCost = singleEggCount * singleEggCost;
        dozenEggCount = inputEgg/12;
        dozenEggCost = dozenEggCount * dozenEggCost;
        totalCost = singleEggCost + dozenEggCost;
        System.out.println("You ordered " + inputEgg + " eggs. That's " + dozenEggCount + " dozen at $3.25 per dozen and " +singleEggCount + " loose eggs at 45 cents each for a total of $" + totalCost);
    }
}
