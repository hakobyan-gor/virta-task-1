package com.task.util.error;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Error {

    private String path;
    private String timestamp;
    private String message;
    private String details;
    private List<ValidationError> errors;
    private int status;

    private Error() {
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Error message(String message) {
        this.message = message;
        return this;
    }

    public Error details(String details) {
        this.details = details;
        return this;
    }

    public Error path(String path) {
        this.path = path;
        return this;
    }

    public Error timestamp() {
        this.timestamp = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now());
        return this;
    }

    public Error requestParams(HttpServletRequest request) {
        ServletWebRequest webRequest = new ServletWebRequest(request);
        final String path = (String) webRequest.getAttribute(RequestDispatcher.ERROR_REQUEST_URI, RequestAttributes.SCOPE_REQUEST);
        this.path = path != null ? path : request.getServletPath();
        return this.timestamp();
    }

    public static Error internal(String message, String errorDescription) {
        Error error = new Error();
        error.message = message;
        error.status = 500;
        error.details = errorDescription;
        return error;
    }

    public static Error internal(String message) {
        return internal(message, "Internal server error");
    }

    public static Error notFound(Throwable t) {
        Error error = new Error();
        error.message = t.getMessage();
        error.status = 404;
        error.details = "Not found";
        return error;
    }

    public static Error notFound(String message, String description) {
        Error error = new Error();
        error.message = message;
        error.status = 404;
        error.details = description;
        return error;
    }

    public static Error notFound(String description) {
        return notFound("Not found", description);
    }

    public static Error forbidden(String message) {
        Error error = new Error();
        error.message = message;
        error.status = 403;
        error.details = "Access denied";
        return error;
    }

    public static Error unauthorized(String message) {
        Error error = new Error();
        error.message = message;
        error.status = 401;
        error.details = "Authorization required";
        return error;
    }

    public static Error badRequest(String message, String description) {
        Error error = new Error();
        error.message = message;
        error.status = 400;
        error.details = description;
        return error;
    }

    public static Error conflict(String message, String description) {
        Error error = new Error();
        error.message = message;
        error.status = 409;
        error.details = description;
        return error;
    }

    public static Error badRequest(String message, List<ValidationError> validationErrors) {
        Error error = new Error();
        error.message = message;
        error.status = 400;
        error.errors = validationErrors;
        error.details = "Validation error";
        return error;
    }

    public static Error badRequest(String message) {
        return badRequest(message, "Bad request");
    }

    public static Error conflict(String message) {
        return conflict(message, "Conflict");
    }

    public static Error unprocessableEntity(String message, String errorDescription) {
        Error error = new Error();
        error.message = message;
        error.status = 422;
        error.details = errorDescription;
        return error;
    }

    public static Error unprocessableEntity(String message) {
        return unprocessableEntity(message, "Unprocessable entity");
    }

    public static Error failedDependency(String message, String errorDescription) {
        Error error = new Error();
        error.message = message;
        error.status = 424;
        error.details = errorDescription;
        return error;
    }

    public static Error tooManyRequests(String message) {
        Error error = new Error();
        error.message = message;
        error.status = 429;
        error.details = "Too many requests";
        return error;
    }

    public static Error notAcceptable(String message) {
        Error error = new Error();
        error.message = message;
        error.status = 406;
        error.details = "Not acceptable";
        return error;
    }

}