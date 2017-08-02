// This program calculates tutition bills as credits times rate per credit hour
package Week3.DebuggingExercisesCh03;

public class DebugThree4
{
   public static void main(String args[])
   {
      int myCredits = 13;
      int yourCredits = 17;
      double rate = 75.84;
      System.out.println("My tuition:");
      tuitionBill(myCredits, rate);
      System.out.println("Your tuition:");
      tuitionBill(myCredits, rate);
   }
   public static void tuitionBill(int c, double r)
   {
      System.out.println("Total due " + (r*c));
   }
}
