package Week6.PracticalExercises;

public class MetalElement extends Element {

    public MetalElement(String symbol, int atomicNumber, double atomicWeight){
        super(symbol, atomicNumber, atomicWeight);
    }

    public String describeElement(){
        return "The symbol is: " + getSymbol() + ", the atomic number is: " + getAtomicNumber() + ", and the atomic weight is: " + getAtomicWeight() + " This element is a metal. Metals are good conducters of electricity";
    }
}
