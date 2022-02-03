package com.example.pushup;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class User implements Parcelable {
    private int id;
    private String fullName;
    private String age;
    private String weight;
    private String height;
    private String goal;
    private String numberOfPushups;
    private String time;

    public User()
    {

    }
    protected User(Parcel in) {
        id = in.readInt();
        fullName = in.readString();
        age = in.readString();
        weight = in.readString();
        height = in.readString();
        goal = in.readString();
        numberOfPushups = in.readString();
        time = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public JSONObject toJSON() {
        JSONObject o = new JSONObject();
        try {
            o.put("id", this.id);
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(fullName);
        parcel.writeString(age);
        parcel.writeString(weight);
        parcel.writeString(height);
        parcel.writeString(goal);
        parcel.writeString(numberOfPushups);
        parcel.writeString(time);
    }
}
