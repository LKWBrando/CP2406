package Week6.PracticalExercises;

public class UseBook {
    public static void main(String[] args) {

        NonFiction book1 = new NonFiction("First Non-Fiction Book");
        book1.setPrice();

        Fiction book2 = new Fiction("First Fiction Book");
        book2.setPrice();

        System.out.println("An example of Non-Fiction Book:" + book1.getBookTitle() + " and the price is: " + book1.getBookPrice());

        System.out.println("An example of Fiction Book:" + book2.getBookTitle() + " and the price is:" + book2.getBookPrice());
    }
}
