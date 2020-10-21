package com.example.myproject1.Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class User {
    public String account;
    public String password;
    public String name;
    public String numberphone;

    public User() {
    }

    public User(String account, String password, String name, String numberphone) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.numberphone = numberphone;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberphone() {
        return numberphone;
    }

    public void setNumberphone(String numberphone) {
        this.numberphone = numberphone;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("account", account);
        result.put("password", password);
        result.put("name", name);
        result.put("numberphone", numberphone);
        return result;
    }
}
