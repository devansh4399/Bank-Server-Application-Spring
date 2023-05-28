package com.cg.bankapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.bankapplication.pojos.Account;

public interface BankApplicationDao  extends JpaRepository<Account,Integer>
{
}
