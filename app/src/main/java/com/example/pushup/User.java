package com.example.pushup;

public class User {
    private String fullName;
    private String age;
    private String weight;
    private String height;
    private String goal;
    private String numberOfPushups;
    private double time;
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getNumberOfPushups() {
        return numberOfPushups;
    }

    public void setNumberOfPushups(String numberOfPushups) {
        this.numberOfPushups = numberOfPushups;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }
}
