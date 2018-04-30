package com.ihm.androide.upmc.manekineko.database;

/**
 * Created by HAMDANI on 29/04/2018.
 */

public class ServerRequest {

    private String operation;
    private User user;

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
