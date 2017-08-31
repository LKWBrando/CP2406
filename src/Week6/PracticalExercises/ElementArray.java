package Week6.PracticalExercises;

public class ElementArray {
    public static void main(String[] args) {
        int count = 0;
        Element[] ElementList = new Element[4];

        ElementList[0] = new MetalElement("CO", 27, 58.933);
        ElementList[1] = new NonMetalElement("H", 1, 1.008);
        ElementList[2] = new MetalElement("AU", 79, 196.97);
        ElementList[3] = new NonMetalElement("HE", 2, 4.0026);

        for (Element i : ElementList){
            System.out.println(i.describeElement());
        }
    }
}
