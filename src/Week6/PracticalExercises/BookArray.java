package Week6.PracticalExercises;

public class BookArray {
    public static void main(String[] args) {
        Book[] BookList = new Book[10];
        int count = 0;

        BookList[0] = new Fiction("Book 1");
        BookList[1] = new NonFiction("Book 2");
        BookList[2] = new Fiction("Book 3");
        BookList[3] = new Fiction("Book 4");
        BookList[4] = new NonFiction("Book 5");
        BookList[5] = new Fiction("Book 6");
        BookList[6] = new Fiction("Book 7");
        BookList[7] = new NonFiction("Book 8");
        BookList[8] = new Fiction("Book 9");
        BookList[9] = new Fiction("Book 10");

        for (Book i : BookList){
            BookList[count].setPrice();
            System.out.println("The book title is:"+ BookList[count].getBookTitle() + " and the price is:$" + BookList[count].getBookPrice());
            count += 1;
        }
    }
}
