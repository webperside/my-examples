package com.webperside.someproject.repository;

import com.webperside.someproject.model.mybatis.Account;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {

    public List<Account> findAllByUserId(Long userId) {

        List<Account> accounts = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Account account = new Account();
            account.setAccountNumber(i + "#1234");
            account.setUserId(userId);
            account.setId(1L);
            account.setAmount(BigDecimal.valueOf(i * 10));

            accounts.add(account);
        }

        return accounts;
    }

    public Optional<Account> findById(long id) {
        Account account = new Account();
        account.setId(id);
        account.setAccountNumber(id + "#1234");
        account.setUserId(id);
        account.setId(1L);
        account.setAmount(BigDecimal.valueOf(id * 10));

        return Optional.of(account);
    }

    public void save(Account account) {

    }

    public void delete(long id) {

    }

    public void transferMoney(long fromId, long toId, BigDecimal amount) {

    }
}
