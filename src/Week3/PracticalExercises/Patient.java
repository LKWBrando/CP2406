package Week3.PracticalExercises;

public class Patient {
    private String patientID;
    private int patientAge;
    private BloodData patientBT;

    public Patient(){
        patientID = "0";
        patientAge = 0;
        patientBT = new BloodData();
    }

    public Patient(String patientID, int patientAge, String bloodtype, String rhFactor){
        this.patientID = patientID;
        this.patientAge = patientAge;
        this.patientBT = new BloodData(bloodtype, rhFactor);
    }

    public String getPatientID() {
        return patientID;
    }

    public int getPatientAge() {

        return patientAge;
    }

    public String getBloodInfo(){
        return patientBT.getBloodtype() + patientBT.getRhFactor();
    }

    public void setBloodInfo(String bloodtype, String rhFactor){
        patientBT.setBloodType(bloodtype);
        patientBT.setRhFactor(rhFactor);
    }
}
