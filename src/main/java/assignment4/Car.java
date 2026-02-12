package assignment4;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String type;
    private int price;
    private boolean rented;

    public Car() {}

    public Car(int i, String bmw, String x5, int i1) {}

    public Car(int id, String brand, String model, String type, int price, boolean rented) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.price = price;
        this.rented = rented;
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public String getType() { return type; }
    public int getPrice() { return price; }
    public boolean isRented() { return rented; }

    public void setId(int id) { this.id = id; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setModel(String model) { this.model = model; }
    public void setType(String type) { this.type = type; }
    public void setPrice(int price) { this.price = price; }
    public void setRented(boolean rented) { this.rented = rented; }
}