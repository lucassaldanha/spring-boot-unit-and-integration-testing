package com.lsoftware.testing.web.to;

import org.hibernate.validator.constraints.NotBlank;

public class CreateClientRequest {

    @NotBlank
    private String name;

    public CreateClientRequest(String name) {
        this.name = name;
    }

    public CreateClientRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
