package com.qedplus.particles.model;

public class UserBO {
    String name;
    String username;
    String currentAccessToken;
    String currentRefreshToken;

    public UserBO(String name, String username, String currentAccessToken, String currentRefreshToken) {
        this.name = name;
        this.username = username;
        this.currentAccessToken = currentAccessToken;
        this.currentRefreshToken = currentRefreshToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCurrentAccessToken() {
        return currentAccessToken;
    }

    public void setCurrentAccessToken(String currentAccessToken) {
        this.currentAccessToken = currentAccessToken;
    }

    public String getCurrentRefreshToken() {
        return currentRefreshToken;
    }

    public void setCurrentRefreshToken(String currentRefreshToken) {
        this.currentRefreshToken = currentRefreshToken;
    }

    @Override
    public String toString() {
        return "UserBO{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", currentAccessToken='" + currentAccessToken + '\'' +
                ", currentRefreshToken='" + currentRefreshToken + '\'' +
                '}';
    }
}
