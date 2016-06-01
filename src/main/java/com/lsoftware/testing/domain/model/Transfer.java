package com.lsoftware.testing.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Transfer extends DomainEntity {

    @ManyToOne(optional = false)
    private Account source;

    @ManyToOne(optional = false)
    private Account target;

    @Column(nullable = false)
    private BigDecimal amount;

    public Account getSource() {
        return source;
    }

    public void setSource(Account source) {
        this.source = source;
    }

    public Account getTarget() {
        return target;
    }

    public void setTarget(Account target) {
        this.target = target;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
