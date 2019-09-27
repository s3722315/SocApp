package com.sept.rest.webservices.restfulwebservices.jwt.resource;

public class SignUpResponse {
    private Boolean success;
    private String message;

    public SignUpResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
