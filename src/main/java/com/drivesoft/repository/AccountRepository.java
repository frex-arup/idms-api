package com.drivesoft.repository;

import com.drivesoft.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    boolean existsByAcctID(Number acctID);

    @Query("SELECT a.acctID FROM Account a")
    List<String> findAllAccountIds();
}
