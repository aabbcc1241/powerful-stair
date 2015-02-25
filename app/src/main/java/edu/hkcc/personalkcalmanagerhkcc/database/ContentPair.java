package edu.hkcc.personalkcalmanagerhkcc.database;

/**
 * Created by beenotung on 2/20/15.
 */
public class ContentPair {
    public String name, value;

    public ContentPair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ContentPair(String name) {
        this(name, "");
    }
}
