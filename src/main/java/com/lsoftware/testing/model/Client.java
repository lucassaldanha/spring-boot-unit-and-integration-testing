package com.lsoftware.testing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Client extends DomainEntity {

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
