package com.example.admin.prjsaturn;

public class Admin extends Left{

    public Admin(int id, String name, String _group, Integer XP) {
        super(id, name, _group, XP);
    }

    @Override
    public int getID() {
        return super.getID();
    }

    @Override
    public void setID(int id) {
        super.setID(id);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void set_name(String _name) {
        super.set_name(_name);
    }

    @Override
    String getGroup() {
        return super.getGroup();
    }

    @Override
    public void setGroup(String Group) {
        super.setGroup(Group);
    }



}
