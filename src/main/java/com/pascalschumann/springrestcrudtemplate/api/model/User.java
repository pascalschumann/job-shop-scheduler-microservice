package com.pascalschumann.springrestcrudtemplate.api.model;

/**
 * For basic auth
 */
public class User {
    private final String userName;
    private final String password;

    public User(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
