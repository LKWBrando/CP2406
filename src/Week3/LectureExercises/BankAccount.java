package Week3.LectureExercises;

public class BankAccount {
    private String bankID;
    private double bankBalance;

    public BankAccount(){
        bankID = "NA";
        bankBalance = 0.0;
    }

    public BankAccount(String bankID, double bankBalance){
        this.bankID = bankID;
        this.bankBalance = bankBalance;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public String getBankID(){
        return bankID;
    }

    public void setBankID(String newBankID){
        bankID = newBankID;
    }

    public void depositBank(Double depositValue){
        bankBalance = bankBalance + depositValue;
    }

    public boolean withdrawBank(Double withdrawValue){
        if(withdrawValue>bankBalance){
            return false;}
            else{bankBalance = bankBalance- withdrawValue;
            return true;}
        }
}
