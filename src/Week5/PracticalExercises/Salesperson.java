package Week5.PracticalExercises;

public class Salesperson {
    private int idNumber;
    private double annualSales;

    public Salesperson(){
        idNumber = 0;
        annualSales = 0.0;
    }

    public Salesperson(int idNumber, Double annualSales){
        this.idNumber = idNumber;
        this.annualSales = annualSales;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public double getAnnualSales() {
        return annualSales;
    }

    public void setAnnualSales(Double annualSales) {
        this.annualSales = annualSales;
    }
}
