package AssignmentOne;

public class Card {
    private int cardIndex;
    private String mineralName;
    private String mineralHD;
    private String mineralSG;
    private String mineralCleavage;
    private String mineralCA;
    private String mineralEcoValue;

    public Card(int cardIndex, String mineralName, String mineralHD, String mineralSG, String mineralCleavage, String mineralCA, String mineralEcoValue){
        this.cardIndex = cardIndex;
        this.mineralName = mineralName;
        this.mineralHD = mineralHD;
        this.mineralSG = mineralSG;
        this.mineralCleavage = mineralCleavage;
        this.mineralCA = mineralCA;
        this.mineralEcoValue = mineralEcoValue;
    }

    public int getCardIndex() {
        return cardIndex;
    }

    public void setCardIndex(int cardIndex) {
        this.cardIndex = cardIndex;
    }

    public String getMineralName() {
        return mineralName;
    }

    public void setMineralName(String mineralName) {
        this.mineralName = mineralName;
    }

    public String getMineralHD() {
        return mineralHD;
    }

    public void setMineralHD(String mineralHD) {
        this.mineralHD = mineralHD;
    }

    public String getMineralSG() {
        return mineralSG;
    }

    public void setMineralSG(String mineralSG) {
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

    public String toString(){
        return "Name: " + mineralName + ", Hardness: " + mineralHD + ", SG: " + mineralSG + ", Cleavage: " +
                mineralCleavage + ", CA: " + mineralCA + ", EV: " + mineralEcoValue;
    }
}
