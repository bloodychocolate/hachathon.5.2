package com.example.admin.prjsaturn;

import java.util.ArrayList;
import java.util.List;

public class User {
    int user_id;
    String first_name;
    String last_name;
    String middle_name;
    String group;
    int xp;
    boolean isAdmin;
    List<Achievment> achievements = new ArrayList<Achievment>();


    public User(int user_id, String first_name, String last_name, String middle_name, String group, int XP, boolean isAdmin) {
        this.user_id = user_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.middle_name = middle_name;
        this.group = group;
        this.xp = XP;
        this.isAdmin = isAdmin;
        this.achievements = achievements;
    }

    public int getID() {
        return this.user_id;
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

    String getGroup() {
        return this.group;

    }

    public void setGroup(String Group) {
        this.group = group;
    }
}