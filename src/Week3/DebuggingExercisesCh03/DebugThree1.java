// This class calculates a waitperson's tip as 15% of the bill
package Week3.DebuggingExercisesCh03;

public class DebugThree1 {
   public static void main(String args[])
   {
      double myCheck = 50.00;
      double yourCheck = 19.95;
      System.out.println("Tips are");
      calcTip(myCheck);
      calcTip(yourCheck);
    }
    private static void calcTip(double bill) {
       double tip;
       final double RATE = 0.15;
       tip = bill + RATE;
       System.out.println("The tip should be at least " + tip);
    }
}
