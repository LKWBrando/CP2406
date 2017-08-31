package Week6.LectureExercises;

public class DiscountedProduct extends Product {
    private double discount;

    public DiscountedProduct(String id, String description, double price, double discount){
        super(id, description, price);
        discount = 0.0;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String toString() {
        return super.toString() + "Discount is:" + this.discount;
    }
}
