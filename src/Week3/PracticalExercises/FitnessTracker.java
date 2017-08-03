package Week3.PracticalExercises;

public class FitnessTracker {
    private String fitnessActivity;
    private Double minutes;
    private String month;
    private int day;
    private int year;

    FitnessTracker(){
        fitnessActivity = "Running";
        minutes = 0.0;
        month = "January";
        day = 1;
        year = 2017;

    }

    FitnessTracker(String fitnessActivity, Double minutes, String month, int day, int year){
        this.fitnessActivity = fitnessActivity;
        this.minutes = minutes;
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public String getFitnessActivity() {
        return fitnessActivity;
    }

    public Double getMinutes() {
        return minutes;
    }

    public String getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }
}
