package edu.hkcc.myutils;

/**
 * Created by beenotung on 2/23/15.
 */
public class Maths {
    public static final float BLS_TO_KG = 0.453592f;

    public static float getBmi(float height, float weight) {
        float heightUnit = height < 100 ? 1 : 0.01f;
        float weightUnit = weight < 120 ? 1 : BLS_TO_KG;
        return (float) ((weight * weightUnit) / Math.pow(height * heightUnit, 2));
    }
}
