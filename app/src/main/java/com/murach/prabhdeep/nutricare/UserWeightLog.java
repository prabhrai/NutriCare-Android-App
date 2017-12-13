package com.murach.prabhdeep.nutricare;

/**
 * Created by jkps on 12/04/2017.
 */

public class UserWeightLog {


    private String username = null;
    private String date = null;
    private Double weight = null;
    private Double bmi = null;

    public UserWeightLog(){}


    public UserWeightLog(String username, String date, Double weight, Double bmi) {
        this.username = username;
        this.date = date;
        this.weight = weight;
        this.bmi = bmi;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }
}
