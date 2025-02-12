package main.java.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class User implements Serializable{

    private static int id = 1;
    private int userId;
    private String userName;
    private String password;

    private static final String FILE_NAME = "users.dat";
    private static Map<String, User> users = loadUsers();

    public User(String userName, String password){
        this.userName = userName;
        this.password = password;
        this.userId = id++;

    }

    public int getId() {
        return this.userId;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public static boolean isValidUsername(String username) {
        return !users.containsKey(username);
    }

    public static boolean registerUser(User user) {
        if (isValidUsername(user.getUsername())) {
            users.put(user.getUsername(), user);
            saveUsers();
            return true;
        }
        return false;
    }

    public static User loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>(); // Return empty map if file doesn't exist or error occurs
        }
    }

    @Override
    public String toString() {
        return "User{id=" + userId + ", username='" + userName + "'}";
    }


}
