package com.nycjv321.hackathon;

import java.util.Optional;

public class Configuration {

    public static Optional<String> getEyesApiKey() {
        return Optional.ofNullable(System.getenv("APPLITOOLS_API_KEY"));
    }
}
