package edu.hkcc.personalkcalmanagerhkcc.database.userinfo;

import edu.hkcc.myutils.Maths;
import edu.hkcc.personalkcalmanagerhkcc.database.ContentPair;

/**
 * Created by beenotung on 2/19/15.
 */
public class UserInfo {
    private static float heightUnit, weightUnit;
    public ContentPair name = new ContentPair("Name");
    public ContentPair age = new ContentPair("Age");
    public ContentPair height = new ContentPair("Height");
    public ContentPair weight = new ContentPair("Weight");
    public ContentPair bmi = new ContentPair("BMI");

    @Deprecated
    public UserInfo() {
        this("", 0, 0, 0);
    }

    public UserInfo(String name, float age, float height, float weight) {
        this.name.value = name;
        this.age.value = String.valueOf(age);
        this.height.value = String.valueOf(height);
        this.weight.value = String.valueOf(weight);
        updateBmi();
    }


    public String getName() {
        return name.value;
    }

    public void setName(String name) {
        this.name.value = name;
    }

    public int getAge() {
        try {
            return Integer.parseInt(age.value);
        } catch (NumberFormatException e) {
            return Math.round(Float.parseFloat(age.value));
        }
    }

    public void setAge(int age) {
        this.age.value = String.valueOf(age);
    }

    public float getHeight() {
        return Float.parseFloat(height.value);
    }

    public void setHeight(float height) {
        this.height.value = String.valueOf(height);
    }

    public float getWeight() {
        return Float.parseFloat(weight.value);
    }

    public void setWeight(float weight) {
        this.weight.value = String.valueOf(weight);
    }

    public float getBmi() {
        updateBmi();
        return Float.parseFloat(bmi.value);
    }

    public void updateBmi() {
        bmi.value = String.valueOf(Maths.getBmi(getHeight(), getWeight()));
    }

    public boolean isSufficient() {
        return getHeight() * getWeight() > 0;
    }
}
