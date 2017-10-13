package com.rh.stravamate.model.datalayer.primitives;

/**
 * Created by robert.hanaway on 13/10/2017.
 */

public class Stats {
    private float averageHeartRate;
    private float averageSpeed;
    private float averageDistance;
    private Activity maxHeart;
    private Activity maxSpeed;
    private Activity minHeart;
    private Activity minSpeed;
    private Activity longestDistance;

    public float getAverageHeartRate() {
        return averageHeartRate;
    }

    public void setAverageHeartRate(float averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public float getAverageDistance() {
        return averageDistance;
    }

    public void setAverageDistance(float averageDistance) {
        this.averageDistance = averageDistance;
    }

    public Activity getMaxHeart() {
        return maxHeart;
    }

    public void setMaxHeart(Activity maxHeart) {
        this.maxHeart = maxHeart;
    }

    public Activity getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Activity maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Activity getMinHeart() {
        return minHeart;
    }

    public void setMinHeart(Activity minHeart) {
        this.minHeart = minHeart;
    }

    public Activity getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Activity minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Activity getLongestDistance() {
        return longestDistance;
    }

    public void setLongestDistance(Activity longestDistance) {
        this.longestDistance = longestDistance;
    }
}
