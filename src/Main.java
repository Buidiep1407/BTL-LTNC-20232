import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Main {
    private static UserManager userManager = new UserManager();
    private static CategoryManager categoryManager = new CategoryManager();
    private static ShoesManager shoesManager = new ShoesManager();

    public static void main(String[] args) {
        JFrame frame = new JFrame("User Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new CardLayout());

        // Custom Login Panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null); // Using null layout for custom positioning

        JLabel welcomeLabel = new JLabel("Chào mừng bạn đến với Phần mềm quản lý Giày dép", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setBounds(50, 30, 700, 30);
        loginPanel.add(welcomeLabel);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        usernameLabel.setBounds(200, 100, 80, 30);
        loginPanel.add(usernameLabel);

        JTextField usernameField = new JTextField();
        usernameField.setBounds(300, 100, 200, 30);
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setBounds(200, 150, 80, 30);
        loginPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(300, 150, 200, 30);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(200, 200, 100, 30);
        loginButton.setBackground(new Color(0, 204, 204));
        loginButton.setForeground(Color.WHITE);
        loginPanel.add(loginButton);

        JButton registerButton = new JButton("Đăng ký");
        registerButton.setBounds(400, 200, 100, 30);
        registerButton.setBackground(new Color(0, 204, 204));
        registerButton.setForeground(Color.WHITE);
        loginPanel.add(registerButton);

        // Admin Panel
        JPanel adminPanel = new JPanel(new BorderLayout());
        JTextArea adminTextArea = new JTextArea();
        adminTextArea.setEditable(false);
        adminTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JButton logoutButton = new JButton("Logout");
        JButton manageShoesButton = new JButton("Manage Shoes");
        JButton manageUsersButton = new JButton("Manage Users");

        JPanel adminButtonPanel = new JPanel(new GridLayout(1, 3));
        adminButtonPanel.add(manageShoesButton);
        adminButtonPanel.add(manageUsersButton);
        adminButtonPanel.add(logoutButton);

        adminPanel.add(new JScrollPane(adminTextArea), BorderLayout.CENTER);
        adminPanel.add(adminButtonPanel, BorderLayout.SOUTH);

        // User Panel
        JPanel userPanel = new JPanel(new BorderLayout());
        JTextArea userTextArea = new JTextArea();
        userTextArea.setEditable(false);
        userTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JButton userLogoutButton = new JButton("Logout");
        JButton displayShoesButton = new JButton("Display Shoes");
        JButton searchShoesButton = new JButton("Search Shoes");

        JPanel userButtonPanel = new JPanel(new GridLayout(1, 3));
        userButtonPanel.add(displayShoesButton);
        userButtonPanel.add(searchShoesButton);
        userButtonPanel.add(userLogoutButton);

        userPanel.add(new JScrollPane(userTextArea), BorderLayout.CENTER);
        userPanel.add(userButtonPanel, BorderLayout.SOUTH);

        // Shoes Panel for Admin
        JPanel shoesPanelAdmin = new JPanel(new BorderLayout());
        DefaultTableModel shoesTableModelAdmin = new DefaultTableModel(new Object[]{"ID", "Brand", "Model", "Size", "Image"}, 0);
        JTable shoesTableAdmin = new JTable(shoesTableModelAdmin);
        TableRowSorter<DefaultTableModel> sorterAdmin = new TableRowSorter<>(shoesTableModelAdmin);
        shoesTableAdmin.setRowSorter(sorterAdmin);
        shoesTableAdmin.getColumnModel().getColumn(4).setCellRenderer(new CustomTableCellRenderer());
        shoesTableAdmin.setRowHeight(60);
        JScrollPane shoesScrollPaneAdmin = new JScrollPane(shoesTableAdmin);

        JPanel shoesSearchPanelAdmin = new JPanel(new GridLayout(1, 2));
        JTextField searchFieldAdmin = new JTextField();
        JButton searchButtonAdmin = new JButton("Search");

        shoesSearchPanelAdmin.add(searchFieldAdmin);
        shoesSearchPanelAdmin.add(searchButtonAdmin);

        JButton addShoeButton = new JButton("Add Shoe");
        JButton editShoeButton = new JButton("Edit Shoe");
        JButton deleteShoeButton = new JButton("Delete Shoe");
        JButton shoesBackButtonAdmin = new JButton("Back");

        JPanel shoesButtonPanelAdmin = new JPanel(new GridLayout(2, 2));
        shoesButtonPanelAdmin.add(addShoeButton);
        shoesButtonPanelAdmin.add(editShoeButton);
        shoesButtonPanelAdmin.add(deleteShoeButton);
        shoesButtonPanelAdmin.add(shoesBackButtonAdmin);

        shoesPanelAdmin.add(shoesSearchPanelAdmin, BorderLayout.NORTH);
        shoesPanelAdmin.add(shoesScrollPaneAdmin, BorderLayout.CENTER);
        shoesPanelAdmin.add(shoesButtonPanelAdmin, BorderLayout.SOUTH);

        // Shoes Panel for User
        JPanel shoesPanelUser = new JPanel(new BorderLayout());
        DefaultTableModel shoesTableModelUser = new DefaultTableModel(new Object[]{"ID", "Brand", "Model", "Size", "Image"}, 0);
        JTable shoesTableUser = new JTable(shoesTableModelUser);
        TableRowSorter<DefaultTableModel> sorterUser = new TableRowSorter<>(shoesTableModelUser);
        shoesTableUser.setRowSorter(sorterUser);
        shoesTableUser.getColumnModel().getColumn(4).setCellRenderer(new CustomTableCellRenderer());
        shoesTableUser.setRowHeight(60);
        JScrollPane shoesScrollPaneUser = new JScrollPane(shoesTableUser);

        JPanel shoesSearchPanelUser = new JPanel(new GridLayout(1, 2));
        JTextField searchFieldUser = new JTextField();
        JButton searchButtonUser = new JButton("Search");

        shoesSearchPanelUser.add(searchFieldUser);
        shoesSearchPanelUser.add(searchButtonUser);

        JButton shoesBackButtonUser = new JButton("Back");

        JPanel shoesButtonPanelUser = new JPanel(new GridLayout(1, 1));
        shoesButtonPanelUser.add(shoesBackButtonUser);

        shoesPanelUser.add(shoesSearchPanelUser, BorderLayout.NORTH);
        shoesPanelUser.add(shoesScrollPaneUser, BorderLayout.CENTER);
        shoesPanelUser.add(shoesButtonPanelUser, BorderLayout.SOUTH);

        // Users Panel
        JPanel usersPanel = new JPanel(new BorderLayout());
        DefaultTableModel usersTableModel = new DefaultTableModel(new Object[]{"Username", "Password", "Admin"}, 0);
        JTable usersTable = new JTable(usersTableModel);
        JScrollPane usersScrollPane = new JScrollPane(usersTable);
        JButton addUserButton = new JButton("Add User");
        JButton editUserButton = new JButton("Edit User");
        JButton deleteUserButton = new JButton("Delete User");
        JButton usersBackButton = new JButton("Back");

        JPanel usersButtonPanel = new JPanel(new GridLayout(2, 2));
        usersButtonPanel.add(addUserButton);
        usersButtonPanel.add(editUserButton);
        usersButtonPanel.add(deleteUserButton);
        usersButtonPanel.add(usersBackButton);

        usersPanel.add(usersScrollPane, BorderLayout.CENTER);
        usersPanel.add(usersButtonPanel, BorderLayout.SOUTH);

        frame.add(loginPanel, "Login");
        frame.add(adminPanel, "Admin");
        frame.add(userPanel, "User");
        frame.add(shoesPanelAdmin, "ShoesAdmin");
        frame.add(shoesPanelUser, "ShoesUser");
        frame.add(usersPanel, "Users");

        CardLayout cl = (CardLayout) frame.getContentPane().getLayout();

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = userManager.authenticate(username, password);
                if (user != null) {
                    if (user.isAdmin()) {
                        adminTextArea.setText("Welcome Admin: " + username);
                        cl.show(frame.getContentPane(), "Admin");
                    } else {
                        userTextArea.setText("Welcome User: " + username);
                        cl.show(frame.getContentPane(), "User");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials");
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and Password cannot be empty");
                    return;
                }
                boolean isAdmin = JOptionPane.showConfirmDialog(frame, "Register as Admin?", "Admin", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION;
                User user = new User(username, password, isAdmin);
                if (userManager.addUser(user)) {
                    JOptionPane.showMessageDialog(frame, "User registered successfully");
                } else {
                    JOptionPane.showMessageDialog(frame, "User with this username and password already exists or invalid input");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(frame.getContentPane(), "Login");
            }
        });

        userLogoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(frame.getContentPane(), "Login");
            }
        });

        manageShoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoesTableModelAdmin.setRowCount(0); // Clear existing rows
                List<Shoes> shoesList = shoesManager.getShoesList();
                for (Shoes shoes : shoesList) {
                    shoesTableModelAdmin.addRow(new Object[]{shoes.getId(), shoes.getBrand(), shoes.getModel(), shoes.getSize(), shoes.getImagePath()});
                }
                cl.show(frame.getContentPane(), "ShoesAdmin");
            }
        });

        displayShoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoesTableModelUser.setRowCount(0); // Clear existing rows
                List<Shoes> shoesList = shoesManager.getShoesList();
                for (Shoes shoes : shoesList) {
                    shoesTableModelUser.addRow(new Object[]{shoes.getId(), shoes.getBrand(), shoes.getModel(), shoes.getSize(), shoes.getImagePath()});
                }
                cl.show(frame.getContentPane(), "ShoesUser");
            }
        });

        searchShoesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shoesTableModelUser.setRowCount(0); // Clear existing rows
                List<Shoes> shoesList = shoesManager.getShoesList();
                for (Shoes shoes : shoesList) {
                    shoesTableModelUser.addRow(new Object[]{shoes.getId(), shoes.getBrand(), shoes.getModel(), shoes.getSize(), shoes.getImagePath()});
                }
                cl.show(frame.getContentPane(), "ShoesUser");
            }
        });

        searchButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchFieldAdmin.getText();
                if (text.trim().length() == 0) {
                    sorterAdmin.setRowFilter(null);
                } else {
                    sorterAdmin.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        searchButtonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = searchFieldUser.getText();
                if (text.trim().length() == 0) {
                    sorterUser.setRowFilter(null);
                } else {
                    sorterUser.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        shoesBackButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(frame.getContentPane(), "Admin");
            }
        });

        shoesBackButtonUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(frame.getContentPane(), "User");
            }
        });

        addShoeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String brand = JOptionPane.showInputDialog(frame, "Enter shoe brand:");
                String model = JOptionPane.showInputDialog(frame, "Enter shoe model:");
                double size = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter shoe size:"));
                String imagePath = selectImage(frame);
                if (imagePath != null) {
                    Shoes newShoes = shoesManager.createShoes(brand, model, size, imagePath);
                    shoesTableModelAdmin.addRow(new Object[]{newShoes.getId(), newShoes.getBrand(), newShoes.getModel(), newShoes.getSize(), newShoes.getImagePath()});
                }
            }
        });

        editShoeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter shoe ID to edit:"));
                String newBrand = JOptionPane.showInputDialog(frame, "Enter new shoe brand:");
                String newModel = JOptionPane.showInputDialog(frame, "Enter new shoe model:");
                double newSize = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter new shoe size:"));
                String newImagePath = selectImage(frame);
                if (newImagePath != null) {
                    Shoes newShoes = new Shoes(id, newBrand, newModel, newSize, newImagePath);
                    if (shoesManager.editShoes(id, newShoes)) {
                        JOptionPane.showMessageDialog(frame, "Shoe updated successfully");
                        shoesTableModelAdmin.setRowCount(0); // Clear existing rows
                        List<Shoes> shoesList = shoesManager.getShoesList();
                        for (Shoes shoes : shoesList) {
                            shoesTableModelAdmin.addRow(new Object[]{shoes.getId(), shoes.getBrand(), shoes.getModel(), shoes.getSize(), shoes.getImagePath()});
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Shoe not found");
                    }
                }
            }
        });

        deleteShoeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter shoe ID to delete:"));
                if (shoesManager.deleteShoes(id)) {
                    JOptionPane.showMessageDialog(frame, "Shoe deleted successfully");
                    shoesTableModelAdmin.setRowCount(0); // Clear existing rows
                    List<Shoes> shoesList = shoesManager.getShoesList();
                    for (Shoes shoes : shoesList) {
                        shoesTableModelAdmin.addRow(new Object[]{shoes.getId(), shoes.getBrand(), shoes.getModel(), shoes.getSize(), shoes.getImagePath()});
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Shoe not found");
                }
            }
        });

        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usersTableModel.setRowCount(0); // Clear existing rows
                List<User> userList = userManager.getUsers();
                for (User user : userList) {
                    usersTableModel.addRow(new Object[]{user.getUsername(), user.getPassword(), user.isAdmin()});
                }
                cl.show(frame.getContentPane(), "Users");
            }
        });

        usersBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(frame.getContentPane(), "Admin");
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(frame, "Enter username:");
                String password = JOptionPane.showInputDialog(frame, "Enter password:");
                if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Username and Password cannot be empty");
                    return;
                }
                boolean isAdmin = JOptionPane.showConfirmDialog(frame, "Is Admin?", "Admin", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION;
                User newUser = new User(username, password, isAdmin);
                if (userManager.addUser(newUser)) {
                    JOptionPane.showMessageDialog(frame, "User added successfully");
                    usersTableModel.addRow(new Object[]{newUser.getUsername(), newUser.getPassword(), newUser.isAdmin()});
                } else {
                    JOptionPane.showMessageDialog(frame, "User with this username and password already exists or invalid input");
                }
            }
        });

        editUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(frame, "Enter username to edit:");
                String newPassword = JOptionPane.showInputDialog(frame, "Enter new password:");
                boolean isAdmin = JOptionPane.showConfirmDialog(frame, "Is Admin?", "Admin", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION;
                if (userManager.editUser(username, newPassword, isAdmin)) {
                    JOptionPane.showMessageDialog(frame, "User updated successfully");
                    usersTableModel.setRowCount(0); // Clear existing rows
                    List<User> userList = userManager.getUsers();
                    for (User user : userList) {
                        usersTableModel.addRow(new Object[]{user.getUsername(), user.getPassword(), user.isAdmin()});
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found");
                }
            }
        });

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog(frame, "Enter username to delete:");
                if (userManager.deleteUser(username)) {
                    JOptionPane.showMessageDialog(frame, "User deleted successfully");
                    usersTableModel.setRowCount(0); // Clear existing rows
                    List<User> userList = userManager.getUsers();
                    for (User user : userList) {
                        usersTableModel.addRow(new Object[]{user.getUsername(), user.getPassword(), user.isAdmin()});
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "User not found");
                }
            }
        });

        frame.setVisible(true);
    }

    private static String selectImage(Component parent) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg", "gif"));
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }
}
