package com.task.util.error;

public class ValidationError
{
    private String code;
    private String field;

    public ValidationError(String field, String code) {
        this.field = field;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
