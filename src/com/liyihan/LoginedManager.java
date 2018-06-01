package com.liyihan;

import java.util.HashMap;
import java.util.Map;

public class LoginedManager {

    private LoginedManager() {
        cookieToUsername = new HashMap<>();
    }

    Map<String, Integer> cookieToUsername;
    private static LoginedManager instance;


    public static LoginedManager getInstance() {
        if (null == instance) {
            instance = new LoginedManager();
        }
        return instance;
    }

    public int getIdByCookie(String cookie) {

        Integer id = cookieToUsername.get(cookie);
        if (null == id) {
            return 0;
        }
        return id;
    }

    public void addLoginedUser(String cookie, int id) {
        cookieToUsername.put(cookie, id);
    }
}
