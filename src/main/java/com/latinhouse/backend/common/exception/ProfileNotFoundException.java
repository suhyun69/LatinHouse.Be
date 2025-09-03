package com.latinhouse.backend.common.exception;

public class ProfileNotFoundException extends RuntimeException {

    public ProfileNotFoundException(String profileId) {
        super("Profile not found: " + profileId);
    }
}