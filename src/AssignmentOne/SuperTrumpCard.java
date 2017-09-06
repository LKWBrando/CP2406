package AssignmentOne;

public class SuperTrumpCard extends Card {
    private String cardDescription;
    private String cardTrumpCategory;

    public SuperTrumpCard(String mineralName, String cardDescription){
        super(mineralName);
        this.cardDescription = cardDescription;
    }

    public String getSuperTrumpCardCat(String mineralName){
        switch(mineralName){
            case "The Geologist":
                cardTrumpCategory = "asdsad";
            case "The Geophysicist":
                cardTrumpCategory = "asdas";
            case "The Mineralogist":
                cardTrumpCategory = "asdsad";
            case "The Gemmologist":
                cardTrumpCategory = "asdasd";
            case "The Petrologist":
                cardTrumpCategory = "asdsad";
            case "The Miner":
                cardTrumpCategory = "asdasd;";
        }
        return  cardTrumpCategory;
    }

    public String toString(){
        return super.toString() + cardDescription;
    }



}
