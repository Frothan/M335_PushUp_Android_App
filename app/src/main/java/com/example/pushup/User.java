package com.example.pushup;

import org.json.JSONObject;

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

    @Override
    public String toString() {
        return "User{" +
                "fullName='" + fullName + '\'' +
                ", age='" + age + '\'' +
                ", weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                ", goal='" + goal + '\'' +
                ", numberOfPushups='" + numberOfPushups + '\'' +
                ", time=" + time +
                '}';
    }

    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        try {
            o.put("fullName", this.fullName);
            o.put("age", this.age);
            o.put("weight", this.weight);
            o.put("height", this.height);
            o.put("goal", this.goal);
            o.put("numberOfPushups", this.numberOfPushups);
            o.put("time", this.time);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    };
}
