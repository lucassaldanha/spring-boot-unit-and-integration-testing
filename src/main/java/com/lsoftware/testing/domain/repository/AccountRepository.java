package com.lsoftware.testing.domain.repository;

import com.lsoftware.testing.domain.model.Account;
import com.lsoftware.testing.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByClient(Client client);

}
