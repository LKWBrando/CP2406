package Week3.LectureExercises;
import java.util.Random;

public class Dice {
    //attributes, private//
    private int faceValue;
    private Random rand;

    //Constructor (To create objects and instances)//
    public Dice(){
        faceValue = 1;
    }

    public int getFaceValue() {
        return faceValue;
    }

    public void roll(){
        rand = new Random();
        faceValue = rand.nextInt(6)+1;
        //facevalue = random.nextInt(max-min+1) +min
    }

}
