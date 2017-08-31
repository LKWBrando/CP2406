package Week6.LectureExercises;

public class Product {
    private String id;
    private String description;
    private double price;

    public Product(){
        id = "NULL";
        description = "NULL";
        price = 0.0;
    }


    public Product(String id, String description, double price){
        this.id = "NULL";
        this.description = "NULL";
        this.price = 0.0;
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

    public String toString(){
        return "The ID is:" + this.getId() + ", The description is:" + this.getDescription() + "The price is:" + this.getPrice();
    }
}
