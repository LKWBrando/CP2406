package AssignmentOne;

public class MineralCard extends Card{
    private double mineralHD;
    private double mineralSG;
    private String mineralCleavage;
    private String mineralCA;
    private String mineralEcoValue;
    public int cardPlayValue = 0;

    public MineralCard(String mineralName, double mineralHD, double mineralSG, String mineralCleavage, String mineralCA, String mineralEcoValue){
        super(mineralName);
        this.mineralHD = mineralHD;
        this.mineralSG = mineralSG;
        this.mineralCleavage = mineralCleavage;
        this.mineralCA = mineralCA;
        this.mineralEcoValue = mineralEcoValue;
    }

    public double getMineralHD() {
        return mineralHD;
    }

    public double getMineralSG() {
        return mineralSG;
    }

    public String getMineralCleavage() {
        return mineralCleavage;
    }

    public String getMineralCA() {
        return mineralCA;
    }

    public String getMineralEcoValue() {
        return mineralEcoValue;
    }

    public int getMineralCleavageValue(String s){
        switch(s){
            case "none":
                cardPlayValue = 0;
            case "poor/none":
                cardPlayValue = 1;
            case "1 poor":
                cardPlayValue = 2;
            case "2 poor":
                cardPlayValue = 3;
            case "1 good":
                cardPlayValue = 4;
            case "1 good/1 poor":
                cardPlayValue = 5;
            case "2 good":
                cardPlayValue = 6;
            case "3 good":
                cardPlayValue = 7;
            case "1 perfect":
                cardPlayValue = 8;
            case "1 perfect/1 good":
                cardPlayValue = 9;
            case "1 perfect/2 good":
                cardPlayValue = 10;
            case "2 perfect/1 good":
                cardPlayValue = 11;
            case "3 perfect":
                cardPlayValue = 12;
            case "4 perfect":
                cardPlayValue = 13;
            case "6 perfect":
                cardPlayValue = 14;
            break;
        }
        return cardPlayValue;
    }

    public int getMineralCaValue(String s){
        switch(s){
            case "ultratrace":
                cardPlayValue = 0;
            case "trace":
                cardPlayValue = 1;
            case "low":
                cardPlayValue = 2;
            case "moderate":
                cardPlayValue = 3;
            case "high":
                cardPlayValue = 4;
            case "very high":
                cardPlayValue = 5;
            break;
        }
        return cardPlayValue;
    }

    public int getEvValue(String s){
        switch(s){
            case "trivial":
                cardPlayValue = 0;
            case "low":
                cardPlayValue = 1;
            case "moderate":
                cardPlayValue = 2;
            case "high":
                cardPlayValue = 3;
            case "very high":
                cardPlayValue = 4;
            case "I'm rich!":
                cardPlayValue = 5;
            break;
        }
        return cardPlayValue;
    }

    public String toString(){
        return super.toString() + ", Hardness: " + mineralHD + ", SG: " + mineralSG + ", Cleavage: " +
                mineralCleavage + ", CA: " + mineralCA + ", EV: " + mineralEcoValue;
    }
}
