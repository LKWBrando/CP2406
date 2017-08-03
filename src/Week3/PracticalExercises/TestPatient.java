package Week3.PracticalExercises;

public class TestPatient {
    public static void main(String[] args) {

        Patient bob = new Patient();
        System.out.println("The patient's ID is: " + bob.getPatientID());
        System.out.println("The patient's age is: " + bob.getPatientAge());
        System.out.println("The patient's bloodtype is : " + bob.getBloodInfo());

        Patient john = new Patient("01", 21, "A", "-");
        System.out.println("\nThe patient's ID is: " + john.getPatientID());
        System.out.println("The patient's age is: " + john.getPatientAge());
        System.out.println("The patient's bloodtype is : " + john.getBloodInfo());


        //john.setBloodInfo("B", "+");
        //System.out.println("\nThe patient's new bloodtype is : " + john.getBloodInfo());
    }
}
