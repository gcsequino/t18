package com.tco.database;

import java.lang.System;

public class Credential {
    final static String USER = "cs314-db";
    final static String PASSWORD = "eiK5liet1uej";
    final static String URL = getUrl();

    private static String getUrl() {
        String useTunnel = System.getenv("CS314_USE_DATABASE_TUNNEL");
        if(useTunnel != null && useTunnel.equals("true")){
            return "jdbc:mariadb://127.0.0.1/cs314";
        }
        return "jdbc:mariadb://faure.cs.colostate.edu/cs314";
    }

    public String url() {return URL;}
}
