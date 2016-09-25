package com.example.admin.prjsaturn;

import java.util.ArrayList;
import java.util.List;

public class User {
    int user_id;
    String first_name;
    String last_name;
    String middle_name;
    int group;
    int xp;
    float income;
    int newClients;
    boolean isAdmin;
    List<Achievment> achievements = new ArrayList<Achievment>();


    public User(int user_id, String first_name, String last_name, String middle_name, int group, int XP, float income, int newClients, boolean isAdmin) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.group = group;
        this.xp = XP;
        this.income = income;
        this.newClients = newClients;
        this.isAdmin = isAdmin;
    }

    public User() {};

    public float getIncome() {
        return income;
    }

    public int getNewClients() {
        return newClients;
    }

    public int getUserid() {
        return user_id;
    }

    public int getXp() {
        return xp;
    }

    public List<Achievment> getAchievements() {
        return achievements;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setIncome(float income) {
        this.income = income;
    }

    public void setNewClients(int newClients) {
        this.newClients = newClients;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void setAchievements(List<Achievment> achievements) {
        this.achievements = achievements;
    }

    /*public void setID(int id) {
        this.user_id = user_id;
    }*/

    public String getName() {
        return this.last_name + " " + this.first_name + " " + this.middle_name;
    }

    /*public void set_name(String _name) {
        this.name = _name;
    }*/

    int getGroup() {
        return this.group;

    }

    public void setGroup(int Group) {
        this.group = group;
    }
}