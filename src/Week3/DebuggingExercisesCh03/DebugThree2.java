// This application displays some math facts
package Week3.DebuggingExercisesCh03;

public class DebugThree2 {

   public static void main(String args[])
   {
      int a = 2, b = 5, c = 10;
      add(a, b);
      add(b, c);
      subtract(c, a);            
   }
   private static void add(int a, int b) {

      System.out.println("The sum of " + a +
         " and " + b + " is " + (a + b));
   }
   private static void subtract(int a, int c) {

      System.out.println("The difference between " +
        c + " and " + a + " is " + (c - a));
   }
}
