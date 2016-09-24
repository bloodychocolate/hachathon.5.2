package com.example.admin.prjsaturn;

public class Left {
    int _id;
    String _name;
    String _group;
    Integer _XP;

    public Left (int id, String name, String _group, Integer XP) {
        this._id = id;
        this._name = name;
        this._group = _group;
        this._XP = XP;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    String getGroup() {
        return this._group;

    }

    public void setGroup(String Group) {
        this._group = _group;
    }
}