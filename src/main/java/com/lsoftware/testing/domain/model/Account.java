package com.lsoftware.testing.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Account extends DomainEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @OneToOne(optional = false)
    private Client client;

    public Account(Client client) {
        this.code = UUID.randomUUID().toString();
        this.client = client;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
