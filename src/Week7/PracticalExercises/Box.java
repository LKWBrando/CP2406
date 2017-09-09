package Week7.PracticalExercises;

public class Box {
    private int numberOfItems;

    public Box(int numberOfItems){
        this.numberOfItems = numberOfItems;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void removeAnItem() throws EmptyBoxException{
        if (numberOfItems > 0){
            numberOfItems -= 1;}
        else{
            throw new EmptyBoxException("The box is empty!");
        }
    }
}
