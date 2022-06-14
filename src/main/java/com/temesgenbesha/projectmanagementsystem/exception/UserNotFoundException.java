package com.temesgenbesha.projectmanagementsystem.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long userId) {
        super("No User found with id: %d".formatted(userId));
    }
}
