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

    public int getEvValue(String s){
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

    public String toString(){
        return super.toString() + String.format("Hardness: %-7.2f |" + "Specific Gravity: %-7.2f |" + "Cleavage: %-18s |"+ "Crystal Abundance: %-13s |"+ "Economic Value: %-10s",mineralHD, mineralSG, mineralCleavage, mineralCA, mineralEcoValue);
    }
}
