package Week6.PracticalExercises;

public abstract class Book {
    private String bookTitle;
    protected double bookPrice;

    public Book(String bookTitle){
        this.bookTitle = bookTitle;
        this.bookPrice = 0.0;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public abstract double setPrice();

}
