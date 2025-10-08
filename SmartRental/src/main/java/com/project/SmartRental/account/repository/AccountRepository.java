package com.project.SmartRental.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.SmartRental.account.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

}
