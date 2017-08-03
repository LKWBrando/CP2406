package Week3.PracticalExercises;

public class BloodData {
    private String bloodtype;
    private String rhFactor;

    BloodData(){
        bloodtype = "O";
        rhFactor = "+";
    }

    BloodData(String bloodtype, String rhFactor){
        this.bloodtype = bloodtype;
        this.rhFactor = rhFactor;
    }

    public String getBloodtype() {

        return bloodtype;
    }

    public String getRhFactor() {
        return rhFactor;
    }

    public void setBloodType(String newBloodType) {
        bloodtype = newBloodType;
    }

    public void setRhFactor(String newRhFactor){
        rhFactor = newRhFactor;
    }

}
