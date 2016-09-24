package com.example.admin.prjsaturn;

import android.app.Application;

/**
 * Created by lred on 24.09.2016.
 */

public class Globals extends Application {
    private User local_user;
    private String username;
    private String password;

    public void setLocalUser(User user){this.local_user = user;}
    public User getLocalUser(){return this.local_user;}

    public void setLocUsername(String username){this.username = username;}
    public String getLocUsername(){return this.username;}

    public void setLocPassword(String password){this.password = password;}
    public String getLocPassword(){return this.password;}

}
