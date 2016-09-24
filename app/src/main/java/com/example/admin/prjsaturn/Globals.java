package com.example.admin.prjsaturn;

import android.app.Application;

/**
 * Created by lred on 24.09.2016.
 */

public class Globals extends Application {
    public User local_user;

    public void setLocalUser(User user)
    {
        this.local_user = user;
    }

    public User getLocalUser(){return local_user;}
}
