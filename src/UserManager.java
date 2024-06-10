import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    private static final String FILE_PATH = "users.txt";
    private User currentUser;

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(User.fromString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveUsers() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null || user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }
        for (User existingUser : users) {
            if (existingUser.getUsername().equals(user.getUsername()) && existingUser.getPassword().equals(user.getPassword())) {
                return false;
            }
        }
        users.add(user);
        saveUsers();
        return true;
    }

    public User authenticate(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                return user;
            }
        }
        return null;
    }

    public boolean editUser(String username, String newPassword, boolean isAdmin) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                users.remove(user);
                users.add(new User(username, newPassword, isAdmin));
                saveUsers();
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String username) {
        User userToRemove = null;
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                userToRemove = user;
                break;
            }
        }
        if (userToRemove != null) {
            users.remove(userToRemove);
            saveUsers();
            return true;
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
