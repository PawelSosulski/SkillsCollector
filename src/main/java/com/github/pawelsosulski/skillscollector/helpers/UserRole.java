package com.github.pawelsosulski.skillscollector.helpers;

public enum UserRole {
    ADMIN("admin"),
    MODERATOR("moderator"),
    BASIC("basic");

    private String name;

    UserRole(String name) {
        this.name = name;
    }
}
