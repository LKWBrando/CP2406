package Week2.PracticalExercises;
import java.text.DecimalFormat;
import java.util.Scanner;

public class MinutesConversion {
    public static void main(String[] args) {
        int inputMinutes;
        double hours;
        double days;
        Scanner userInputMinutes = new Scanner(System.in);
        System.out.println("Input the number of minutes.");
        inputMinutes = userInputMinutes.nextInt();
        hours = inputMinutes / 60;
        days = hours / 24;
        System.out.printf("%d minutes equals %.3f hours and equals %.3f days", inputMinutes,hours,days);
    }
}
