package com.liyihan;

import java.util.HashMap;
import java.util.Map;

public class BackLoginedManager {

    private static BackLoginedManager instance;
    private Map<String, Integer> cookieToAdminId;

    public static BackLoginedManager getInstance() {
        if (instance == null) {
            instance = new BackLoginedManager();
        }
        return instance;
    }

    private BackLoginedManager() {
        cookieToAdminId = new HashMap<>();
    }

    public int getIdByCookie(String cookie) {
        Integer id = cookieToAdminId.get(cookie);
        if (null == id) {
            return 0;
        }
        return id;
    }

    public void addLoginedAdmin(String cookie, int id) {
        cookieToAdminId.put(cookie, id);
    }

}
