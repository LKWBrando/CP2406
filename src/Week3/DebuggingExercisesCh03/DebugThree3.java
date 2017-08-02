// This application gets a user's name and displays a greeting
package Week3.DebuggingExercisesCh03;

import java.util.Scanner;
public class DebugThree3
{
   public static void main(String args[])
   {
      String name;
      name = getName();
      displayGreeting(name);           
   }
   public static String getName()
   {
      String name;
      Scanner input = new Scanner(System.in);
      System.out.print("Enter name");
      name = input.next();
      return name;
   }
   public static void displayGreeting(String name)
   {
      System.out.println("Hello, " + name + "!");
   }
}
