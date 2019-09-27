package com.sept.rest.webservices.restfulwebservices.jwt.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {

    @NotBlank
    @Size(min = 1, max = 20)
    private String username;

    @NotBlank
    @Size(min = 1, max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
