// Some circle statistics
package Week3.DebuggingExercisesCh04;

public class DebugFour2
{
   public static void main(String args[])
   {
      Double radius = 12.6;
      System.out.println("Circle statistics");
      double area = java.lang.Math.PI * radius * radius;
      System.out.println("area is " + area);
      double diameter = 2 * radius;
      System.out.println("diameter is " + diameter);
   }
}
