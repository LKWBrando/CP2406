package AssignmentOne;

public class Card {
    private String mineralName;
    private int mineralHD;
    private double mineralSG;
    private String mineralCleavage;
    private String mineralCA;
    private String mineralEcoValue;

    public Card(String mineralName, int mineralHD, double mineralSG, String mineralCleavage, String mineralCA, String mineralEcoValue){
        this.mineralName = mineralName;
        this.mineralHD = mineralHD;
        this.mineralSG = mineralSG;
        this.mineralCleavage = mineralCleavage;
        this.mineralCA = mineralCA;
        this.mineralEcoValue = mineralEcoValue;
    }

    public String getMineralName() {
        return mineralName;
    }

    public void setMineralName(String mineralName) {
        this.mineralName = mineralName;
    }

    public int getMineralHD() {
        return mineralHD;
    }

    public void setMineralHD(int mineralHD) {
        this.mineralHD = mineralHD;
    }

    public double getMineralSG() {
        return mineralSG;
    }

    public void setMineralSG(double mineralSG) {
        this.mineralSG = mineralSG;
    }

    public String getMineralCleavage() {
        return mineralCleavage;
    }

    public void setMineralCleavage(String mineralCleavage) {
        this.mineralCleavage = mineralCleavage;
    }

    public String getMineralCA() {
        return mineralCA;
    }

    public void setMineralCA(String mineralCA) {
        this.mineralCA = mineralCA;
    }

    public String getMineralEcoValue() {
        return mineralEcoValue;
    }

    public void setMineralEcoValue(String mineralEcoValue) {
        this.mineralEcoValue = mineralEcoValue;
    }
}
