package com.example.admin.prjsaturn;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context)
            throws JsonParseException {

        final JsonObject jsonObject = json.getAsJsonObject();
        JsonElement User_id = jsonObject.get("user_id");
        JsonElement first_name = jsonObject.get("first_name");
        JsonElement last_name = jsonObject.get("last_name");
        JsonElement middle_name = jsonObject.get("middle_name");
        JsonElement income = jsonObject.get("income");
        JsonElement xp = jsonObject.get("xp");
        JsonElement newClients = jsonObject.get("new_clients");
        JsonElement isAdmin = jsonObject.get("is_admin");
        JsonElement group = jsonObject.get("group");

        final User user = new User();
        user.setUserId(User_id.getAsInt());
        user.setAdmin(isAdmin.getAsBoolean());
        user.setFirst_name(first_name.getAsString());
        user.setLast_name(last_name.getAsString());
        user.setMiddle_name(middle_name.getAsString());
        user.setIncome(income.getAsFloat());
        user.setNewClients(newClients.getAsInt());
        user.setGroup(group.getAsInt());

        return user;
    }
}