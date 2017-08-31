package Week6.LectureExercises;

public class Discounted {
    private String id;
    private String description;
    private double price;
    private double discount;

    public Discounted(String id, String description, double price, double discount) {
        id = "NULL";
        description = "Null";
        price = 0.0;
        discount = 0.0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String toString(){
        return "The ID is:" + this.getId() + ", The description is:" + this.getDescription() + "The price is:" + this.getPrice() + "The discount is: " + this.discount;
    }

}
