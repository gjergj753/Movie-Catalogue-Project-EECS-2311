package main.model;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Badge implements Serializable {
    private static int id = 1;
    private int badgeId;
    private String name;
    private String description;
    private String criteria;

    private static final String FILE_NAME = "badges.dat";
    private static Map<Integer, Badge> badges = loadBadges();

    public Badge(String name, String description, String criteria) {
        this.badgeId = id++;
        this.name = name;
        this.description = description;
        this.criteria = criteria;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public static boolean addBadge(Badge badge) {
        if (!badges.containsKey(badge.getBadgeId())) {
            badges.put(badge.getBadgeId(), badge);
            saveBadges();
            return true;
        }
        return false;
    }

    public static Badge getBadge(int badgeId) {
        return badges.get(badgeId);
    }

    private static void saveBadges() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(badges);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Badge> loadBadges() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<Integer, Badge>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>(); // Return empty map if file doesn't exist or error occurs
        }
    }

    public static void initializeDefaultBadges() {
        addBadge(new Badge("1 Year Award", "Awarded for 1 year of membership", "membership_duration >= 1"));
        addBadge(new Badge("5 Year Award", "Awarded for 5 years of membership", "membership_duration >= 5"));
        addBadge(new Badge("10 Year Award", "Awarded for 10 years of membership", "membership_duration >= 10"));

        addBadge(new Badge("20 Movies Watched", "Awarded for watching 20 movies", "movies_watched >= 20"));
        addBadge(new Badge("50 Movies Watched", "Awarded for watching 50 movies", "movies_watched >= 50"));
        addBadge(new Badge("100 Movies Watched", "Awarded for watching 100 movies", "movies_watched >= 100"));
    }

    @Override
    public String toString() {
        return "Badge{badgeId=" + badgeId + ", name='" + name + "', description='" + description + "', criteria='" + criteria + "'}";
    }
}

