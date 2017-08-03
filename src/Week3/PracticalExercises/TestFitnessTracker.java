package Week3.PracticalExercises;

import com.sun.xml.internal.ws.policy.spi.PolicyAssertionValidator;

public class TestFitnessTracker {
    public static void main(String[] args) {

        FitnessTracker exerciseOne = new FitnessTracker();
        System.out.println("The activity is: " + exerciseOne.getFitnessActivity());
        System.out.println("The number of minutes spent participating is: " + exerciseOne.getMinutes());
        System.out.println("The date is: " + exerciseOne.getDay() + " " + exerciseOne.getMonth() + " " + exerciseOne.getYear());

        FitnessTracker exerciseTwo = new FitnessTracker("Jogging", 10.0, "April", 20, 2018);
        System.out.println("The activity is: " + exerciseTwo.getFitnessActivity());
        System.out.println("The number of minutes spent participating is: " + exerciseTwo.getMinutes());
        System.out.println("The date is: " + exerciseTwo.getDay() + " " + exerciseTwo.getMonth() + " " + exerciseTwo.getYear());
    }
}
