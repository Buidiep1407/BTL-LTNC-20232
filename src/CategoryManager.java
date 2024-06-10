import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {
    private List<Category> categories;
    private static final String FILE_PATH = "categories.txt";

    public CategoryManager() {
        categories = new ArrayList<>();
        loadCategories();
    }

    private void loadCategories() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                categories.add(Category.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCategories() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Category category : categories) {
                writer.write(category.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public boolean addCategory(Category category) {
        if (categories.contains(category)) {
            return false;
        }
        categories.add(category);
        saveCategories();
        return true;
    }

    public boolean editCategory(String oldName, String newName) {
        for (Category category : categories) {
            if (category.getName().equals(oldName)) {
                category = new Category(newName);
                saveCategories();
                return true;
            }
        }
        return false;
    }

    public boolean deleteCategory(String name) {
        Category categoryToRemove = null;
        for (Category category : categories) {
            if (category.getName().equals(name)) {
                categoryToRemove = category;
                break;
            }
        }
        if (categoryToRemove != null) {
            categories.remove(categoryToRemove);
            saveCategories();
            return true;
        }
        return false;
    }
}
