package AssignmentOne;

public class Card {
    private String mineralName;
     public Card(String mineralName){
         this.mineralName = mineralName;
     }

    public String getMineralName() {
        return mineralName;
    }

    public String toString(){
         return "Name: " + mineralName;
    }
}
