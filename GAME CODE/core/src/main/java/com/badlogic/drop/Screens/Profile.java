package com.badlogic.drop.Screens;

import java.io.*;

public class Profile {
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
    public static void saveProfiles(Profile profile, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(profile.getName() + "," + profile.getLevel());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load profiles from a file
    // Load profiles from a file
    public static Profile loadProfile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int level = Integer.parseInt(parts[1]);
                    return new Profile(name, level);
                }
            }
        } catch (IOException e) {
            System.out.println("Profile failed to load");
        } catch (NumberFormatException e) {
            System.out.println("Profile failed to parse file");
        }
        return null;
    }

}
