package com.temesgenbesha.projectmanagementsystem.entity;

public enum Status {
    IN_PROGRESS("in_progress"), DONE("done"), OPEN("open");

    private final String name;
    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
