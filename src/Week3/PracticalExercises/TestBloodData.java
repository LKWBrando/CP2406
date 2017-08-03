package Week3.PracticalExercises;

public class TestBloodData {
    public static void main(String[] args) {

        BloodData bloodOne = new BloodData();
        System.out.println("The blood type is: " + bloodOne.getBloodtype() + bloodOne.getRhFactor());

        bloodOne.setBloodType("A");
        bloodOne.setRhFactor("-");
        System.out.println("The new blood type is: " + bloodOne.getBloodtype() + bloodOne.getRhFactor());
    }
}
