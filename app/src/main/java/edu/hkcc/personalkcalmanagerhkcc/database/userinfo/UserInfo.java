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

    public UserInfo(String name,float age,float height,float weight,float bmi) {
        this.name.value=name;
        this.age.value= String.valueOf(age);
        this.height.value= String.valueOf(height);
        this.weight.value=String.valueOf(weight);
        this.bmi.value=String.valueOf(bmi);
    }
    public String getName(){
        return name.value;
    }
    public
}
