public class Shoes {
    private int id;
    private String brand;
    private String model;
    private double size;
    private String imagePath;

    public Shoes(int id, String brand, String model, double size, String imagePath) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.size = size;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getSize() {
        return size;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return id + "," + brand + "," + model + "," + size + "," + imagePath;
    }

    public static Shoes fromString(String line) {
        String[] parts = line.split(",");
        return new Shoes(Integer.parseInt(parts[0]), parts[1], parts[2], Double.parseDouble(parts[3]), parts[4]);
    }
}
