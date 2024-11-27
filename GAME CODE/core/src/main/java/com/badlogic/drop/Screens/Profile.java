package com.badlogic.drop.Screens;

import java.io.*;

public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int level;

    public Profile(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    // Save profiles to a file
    public static void saveProfiles(Profile[] profiles, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(profiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load profiles from a file
    public static Profile[] loadProfiles(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Profile[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
