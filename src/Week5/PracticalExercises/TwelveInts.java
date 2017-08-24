package Week5.PracticalExercises;

public class TwelveInts {
    public static void main(String[] args) {
        int [] intArray = {2,3,4,5,6,7,8,9,10,11,12,13};

        System.out.println("The ascending order is:");
        for (int i = 0; i < intArray.length; i++){
            System.out.print(intArray[i]+ " ");
        }

        System.out.println("\n The descending order is:");
        for (int i = intArray.length; i > 0; i--){
            System.out.print(intArray[i-1] + " ");
        }
    }
}
