package com.murach.prabhdeep.nutricare;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class User {

    private String username = null;
    private String first_name = null;
    private String last_name = null;
    private Double height = null;
    private Double initial_weight = null;
    private Double goal_weight = null;
    private String goal_type = null;
    private Double initial_bmi = null;
    private String address = null;
    private String password = null;

    public User(String username, String first_name, String last_name, Double height, Double initial_weight, Double goal_weight, String goal_type, Double initial_bmi, String address, String password) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.height = height;
        this.initial_weight = initial_weight;
        this.goal_weight = goal_weight;
        this.goal_type = goal_type;
        this.initial_bmi = initial_bmi;
        this.address = address;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getInitial_weight() {
        return initial_weight;
    }

    public void setInitial_weight(Double initial_weight) {
        this.initial_weight = initial_weight;
    }

    public Double getGoal_weight() {
        return goal_weight;
    }

    public void setGoal_weight(Double goal_weight) {
        this.goal_weight = goal_weight;
    }

    public String getGoal_type() {
        return goal_type;
    }

    public void setGoal_type(String goal_type) {
        this.goal_type = goal_type;
    }

    public Double getInitial_bmi() {
        return initial_bmi;
    }

    public void setInitial_bmi(Double initial_bmi) {
        this.initial_bmi = initial_bmi;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}