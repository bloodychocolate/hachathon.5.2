package com.example.admin.prjsaturn;

public class User {
    int id;
    String name;
    String group;
    int xp;
    boolean isAdmin;

    public User(int id, String name, String group, int XP, boolean isAdmin) {
        this.id = id;
        this.name = name;
        this.group = group;
        this.xp = XP;
        this.isAdmin = isAdmin;
    }

    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void set_name(String _name) {
        this.name = _name;
    }

    String getGroup() {
        return this.group;

    }

    public void setGroup(String Group) {
        this.group = group;
    }
}