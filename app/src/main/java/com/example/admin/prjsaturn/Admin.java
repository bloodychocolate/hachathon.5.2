package com.example.admin.prjsaturn;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ADMIN on 25.09.2016.
 */

public class Admin {

    protected Boolean doInBackground(Void... params) {
        String url = "http://projectsaturn.tk.host1512310.serv66.hostland.pro/api.php";
        String charset = "UTF-16";

        String query = String.format("login=%s&pass=%s&method=getUserInfo",
                mEmail,
                mPassword);
        String TSTR;
        URLConnection connection;
        InputStream response;
        try {
            connection = new URL(url + "?" + query).openConnection();
            connection.setRequestProperty("Accept-Charset", charset);
            response = connection.getInputStream();

            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(User.class, new UserDeserializer());
            Gson gson  = gsonBuilder.create();

            String resp = convertStreamToString(response);
            resp = resp.substring(1,resp.length()-1);
            User user = gson.fromJson(resp, User.class);

            Globals appState = ((Globals)getApplicationContext());
            appState.setLocalUser(user);
        }catch(java.io.IOException e){};


        for (String credential : DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            if (pieces[0].equals(mEmail)) {
                // Account exists, return true if the password matches.
                return pieces[1].equals(mPassword);
            }
        }

        // TODO: register the new account here.
        return true;
    }



}
