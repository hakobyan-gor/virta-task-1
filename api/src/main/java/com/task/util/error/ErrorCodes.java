package com.task.util.error;

public class ErrorCodes {

    private ErrorCodes() {
    }

    public static final String NOT_FOUND = "not.found";
    public static final String BAD_REQUEST = "bad.request";

    public static class Company {

        public static final String ALREADY_EXISTS = "Company with given name already exists.";

    }

}