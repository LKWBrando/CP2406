package AssignmentOne;

//The MineralCard Class is the sub class of the Card Class, used to create objects that have the base variable of mineralName
public class MineralCard extends Card{
    /*Additional variables involved are:
    1. mineralHD, used to store the double values of the MineralCard object's hardness, as read from the txt file.
    2. mineralSG, used to store the double values of the MineralCard object's Specific Gravity, as read from the txt file.
    3. mineralCleavage, used to store the String values of the MineralCard object's Cleavage, as read from the txt file.
    4. mineralCA, used to store the String values of the MineralCard object's Crystal Abundance as read from the txt file.
    5. mineralEcoValue, used to store the String values of the MineralCard object's Economic Value, as read from the txt file.
    6. cardPlayValue, used to store the value of a card with String values, that is converted to a int used to compare the ranking of the String values.
    */
    private double mineralHD;
    private double mineralSG;
    private String mineralCleavage;
    private String mineralCA;
    private String mineralEcoValue;
    private int cardPlayValue = 0;

    //Constructor for the MineralCard Class
    public MineralCard(String mineralName, double mineralHD, double mineralSG, String mineralCleavage, String mineralCA, String mineralEcoValue){
        super(mineralName);
        this.mineralHD = mineralHD;
        this.mineralSG = mineralSG;
        this.mineralCleavage = mineralCleavage;
        this.mineralCA = mineralCA;
        this.mineralEcoValue = mineralEcoValue;
    }

    //Getter for the double variable mineralHD
    public double getMineralHD() {
        return mineralHD;
    }

    //Getter for the double variable mineralSG
    public double getMineralSG() {
        return mineralSG;
    }

    //Getter for the String variable mineralCleavage
    public String getMineralCleavage() {
        return mineralCleavage;
    }

    //Getter for the String variable mineralCA
    public String getMineralCA() {
        return mineralCA;
    }

    //Getter for the String variable mineralEcoValue
    public String getMineralEcoValue() {
        return mineralEcoValue;
    }

    //Method to convert the String mineralCleavage values to integer values, as per their ranking with each other.
    public int getMineralCleavageValue(String s){
        switch(s){
            case "none":
                cardPlayValue = 1;
                break;
            case "poor/none":
                cardPlayValue = 2;
                break;
            case "1 poor":
                cardPlayValue = 3;
                break;
            case "2 poor":
                cardPlayValue = 4;
                break;
            case "1 good":
                cardPlayValue = 5;
                break;
            case "1 good/1 poor":
                cardPlayValue = 6;
                break;
            case "2 good":
                cardPlayValue = 7;
                break;
            case "3 good":
                cardPlayValue = 8;
                break;
            case "1 perfect":
                cardPlayValue = 9;
                break;
            case "1 perfect/1 good":
                cardPlayValue = 10;
                break;
            case "1 perfect/2 good":
                cardPlayValue = 11;
                break;
            case "2 perfect/1 good":
                cardPlayValue = 12;
                break;
            case "3 perfect":
                cardPlayValue = 13;
                break;
            case "4 perfect":
                cardPlayValue = 14;
                break;
            case "6 perfect":
                cardPlayValue = 15;
                break;
            }
        return cardPlayValue;
    }

    //Method to convert the String mineralCA values to integer values, as per their ranking with each other.
    public int getMineralCaValue(String s){
        switch(s){
            case "ultratrace":
                cardPlayValue = 1;
                break;
            case "trace":
                cardPlayValue = 2;
                break;
            case "low":
                cardPlayValue = 3;
                break;
            case "moderate":
                cardPlayValue = 4;
                break;
            case "high":
                cardPlayValue = 5;
                break;
            case "very high":
                cardPlayValue = 6;
                break;
            }
        return cardPlayValue;
    }

    ////Method to convert the String mineralEcoValue values to integer values, as per their ranking with each other.
    public int getMineralEvValue(String s){
        switch(s){
            case "trivial":
                cardPlayValue = 1;
                break;
            case "low":
                cardPlayValue = 2;
                break;
            case "moderate":
                cardPlayValue = 3;
                break;
            case "high":
                cardPlayValue = 4;
                break;
            case "very high":
                cardPlayValue = 5;
                break;
            case "I'm rich!":
                cardPlayValue = 6;
                break;
            }
        return cardPlayValue;
    }

    //Overwritten toString() to display all the properties of the Card object
    public String toString(){
        return super.toString() + String.format("Hardness: %-7.2f |" + "Specific Gravity: %-7.2f |" + "Cleavage: %-18s |"+ "Crystal Abundance: %-13s |"+ "Economic Value: %-10s",mineralHD, mineralSG, mineralCleavage, mineralCA, mineralEcoValue);
    }
}
