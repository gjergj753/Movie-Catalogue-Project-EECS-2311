package main.java.model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class UserBadge implements Serializable {
    private int userId;
    private int badgeId;
    private LocalDateTime earnedAt;

    private static final String FILE_NAME = "userbadges.dat";
    private static Map<String, UserBadge> userBadges = loadUserBadges();

    public UserBadge(int userId, int badgeId) {
        this.userId = userId;
        this.badgeId = badgeId;
        this.earnedAt = LocalDateTime.now();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(int badgeId) {
        this.badgeId = badgeId;
    }

    public LocalDateTime getEarnedAt() {
        return earnedAt;
    }

    public void setEarnedAt(LocalDateTime earnedAt) {
        this.earnedAt = earnedAt;
    }

    private static String generateKey(int userId, int badgeId) {
        return userId + "-" + badgeId;
    }

    public static boolean addUserBadge(UserBadge userBadge) {
        String key = generateKey(userBadge.getUserId(), userBadge.getBadgeId());
        if (!userBadges.containsKey(key)) {
            userBadges.put(key, userBadge);
            saveUserBadges();
            return true;
        }
        return false;
    }

    public static UserBadge getUserBadge(int userId, int badgeId) {
        return userBadges.get(generateKey(userId, badgeId));
    }

    private static void saveUserBadges() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(userBadges);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<String, UserBadge> loadUserBadges() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, UserBadge>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>(); // Return empty map if file doesn't exist or error occurs
        }
    }

    @Override
    public String toString() {
        return "UserBadge{userId=" + userId + ", badgeId=" + badgeId + ", earnedAt=" + earnedAt + "}";
    }
}

