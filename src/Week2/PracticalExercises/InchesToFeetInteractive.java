package Week2.PracticalExercises;
import java.util.Scanner;

public class InchesToFeetInteractive {
    public static void main(String[] args) {
        double inches;
        double feet;
        Scanner inputInches = new Scanner(System.in);
        System.out.println("Input your length in inches");
        inches = inputInches.nextDouble();
        feet = inches%12;
        inches = 86/12;
        System.out.println(feet + "feet " + inches + "inches");
    }
}
