package Week6.PracticalExercises;

public class NonMetalElement extends Element {

    public NonMetalElement(String symbol, int atomicNumber, double atomicWeight){
        super(symbol, atomicNumber, atomicWeight);
    }

    public String describeElement(){
        return "The symbol is: " + getSymbol() + ", the atomic number is: " + getAtomicNumber() + ", and the atomic weight is: " + getAtomicWeight() + "This element is a non-metal. Non-Metals are poor conducters of electricity";
    }
}

