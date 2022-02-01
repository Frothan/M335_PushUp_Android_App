package com.example.pushup;

public class User {
    private String fullName;
    private int goal;
    private int numberOfPushups;
    private double time;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getNumberOfPushups() {
        return numberOfPushups;
    }

    public void setNumberOfPushups(int numberOfPushups) {
        this.numberOfPushups = numberOfPushups;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
