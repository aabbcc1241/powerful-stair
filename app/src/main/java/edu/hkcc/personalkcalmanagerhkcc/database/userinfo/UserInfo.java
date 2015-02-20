package edu.hkcc.personalkcalmanagerhkcc.database.userinfo;

import edu.hkcc.personalkcalmanagerhkcc.database.ContentPair;

/**
 * Created by beenotung on 2/19/15.
 */
public class UserInfo {
    public ContentPair name = new ContentPair("Name");
    public ContentPair age = new ContentPair("Age");
    public ContentPair height = new ContentPair("Height");
    public ContentPair weight = new ContentPair("Weight");
    public ContentPair bmi = new ContentPair("BMI");
}
