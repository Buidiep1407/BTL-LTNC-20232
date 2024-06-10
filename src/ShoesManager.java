import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoesManager {
    private List<Shoes> shoesList;
    private static final String FILE_PATH = "shoes.txt";
    private int nextId;

    public ShoesManager() {
        shoesList = new ArrayList<>();
        loadShoes();
        nextId = shoesList.size() > 0 ? shoesList.get(shoesList.size() - 1).getId() + 1 : 1;
    }

    private void loadShoes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                shoesList.add(Shoes.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveShoes() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Shoes shoes : shoesList) {
                writer.write(shoes.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Shoes> getShoesList() {
        return shoesList;
    }

    public boolean addShoes(Shoes shoes) {  
        shoesList.add(shoes);
        saveShoes();
        return true;
    }

    public Shoes createShoes(String brand, String model, double size, String imagePath) {
        Shoes shoes = new Shoes(nextId++, brand, model, size, imagePath);
        addShoes(shoes);
        return shoes;
    }

    public boolean editShoes(int id, Shoes newShoes) {
        for (Shoes shoes : shoesList) {
            if (shoes.getId() == id) {
                shoesList.remove(shoes);
                shoesList.add(newShoes);
                saveShoes();
                return true;
            }
        }
        return false;
    }

    public boolean deleteShoes(int id) {
        Shoes shoesToRemove = null;
        for (Shoes shoes : shoesList) {
            if (shoes.getId() == id) {
                shoesToRemove = shoes;
                break;
            }
        }
        if (shoesToRemove != null) {
            shoesList.remove(shoesToRemove);
            saveShoes();
            return true;
        }
        return false;
    }
}
