package com.nycjv321.hackathon.models;

import java.util.Optional;

public class Credentials {
    private Optional<String> userName;
    private Optional<String> password;


    protected Credentials(Optional<String> userName, Optional<String> password) {
        this.userName = userName;
        this.password = password;
    }

    public static Credentials create(Optional<String> userName, Optional<String> password) {
        return new Credentials(userName, password);
    }

    public boolean areInvalid() {
        return !userName.isPresent() || !password.isPresent();
    }

    public Optional<String> getUserName() {
        return userName;
    }

    public Optional<String> getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "userName=" + userName +
                '}';
    }
}

