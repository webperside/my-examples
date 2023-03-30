package com.webperside.someproject.service.v2.functional;

import com.webperside.someproject.model.dto.NotificationDto;
import com.webperside.someproject.model.mybatis.Account;
import com.webperside.someproject.model.mybatis.User;
import com.webperside.someproject.model.request.AccountRequest;
import com.webperside.someproject.repository.AccountRepository;
import com.webperside.someproject.service.NotificationService;
import com.webperside.someproject.service.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountServiceV2 {

    private final AccountRepository accountRepository;

    public AccountServiceV2(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccountsByUserId(long userId) {
        return accountRepository.findAllByUserId(userId);
    }

    public List<Account> getAllEligibleAccountsByUserId(long userId) {
        return getAllAccountsByUserId(userId).stream().filter(a -> a.getAmount().longValue() >= 0).toList();
    }

    public void saveAllAccounts(long userId, List<AccountRequest> accounts) {
        // account save
    }

    public void delete(long id) {
        accountRepository.delete(id);
    }

    public void transferMoney(long fromId, long toId, BigDecimal amount) {
        accountRepository.transferMoney(fromId, toId, amount);
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
