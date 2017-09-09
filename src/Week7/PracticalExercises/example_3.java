package Week7.PracticalExercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class example_3 {
    public static void main(String[] args) throws IOException{
        int totalAgeCount = 0;
        int totalStudentCount = 0;

        FileReader newFile = new FileReader("C:\\Users\\user\\IdeaProjects\\CP2406\\src\\Week7\\PracticalExercises\\age.txt");
        BufferedReader newBR = new BufferedReader(newFile);

        String nextLine;
        while ((nextLine = newBR.readLine()) != null) {
            int studentAge = Integer.parseInt(nextLine);
            totalAgeCount += studentAge;
            totalStudentCount += 1;
        }

        try{
            System.out.println("The average age of the students are : " + (totalAgeCount/totalStudentCount));}
            catch(ArithmeticException e){
                System.out.println("Error!");
        }
    }
}
