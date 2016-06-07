package com.lsoftware.testing.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Client extends DomainEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String number;

    @OneToOne
    private Account account;

    public Client(String name) {
        this.name = name;
        this.number = UUID.randomUUID().toString();
    }

    Client() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
