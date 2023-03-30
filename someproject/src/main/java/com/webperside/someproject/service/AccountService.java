package com.webperside.someproject.service;

import com.webperside.someproject.model.dto.NotificationDto;
import com.webperside.someproject.model.mybatis.Account;
import com.webperside.someproject.model.mybatis.User;
import com.webperside.someproject.model.request.AccountRequest;
import com.webperside.someproject.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserService userService;
    private final NotificationService notificationService;

    public AccountService(AccountRepository accountRepository, UserService userService, NotificationService notificationService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public List<Account> getAllEligibleAccountsByUserId(long userId) {
        return accountRepository.findAllByUserId(userId)
                .stream().filter(a -> a.getAmount().longValue() >= 0).toList();
    }

    public List<Account> getAllAccountsByUserId(long userId) {
        return accountRepository.findAllByUserId(userId);
    }

    public void saveAllAccounts(long userId, List<AccountRequest> accounts) {

    }

    public void delete(long id) {
        Account account = findAccountById(id);

        if (!account.getAmount().equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Account cannot be deleted");
        }

        accountRepository.delete(id);
    }

    public void transferMoney(long fromId, long toId, BigDecimal amount) {
        Account from = findAccountById(fromId);

        if (from.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("Account doesn't have enough money");
        }

        accountRepository.transferMoney(fromId, toId, amount);

        Account to = findAccountById(toId);

        notificationService.send(
                prepareNotificationDto(
                        userService.findUserById(to.getUserId()),
                        to,
                        amount
                )
        );
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // private util methods
    public NotificationDto prepareNotificationDto(User u, Account account, BigDecimal amount) {
        String title = "Transferring money";
        String content = "Dear, %s %s. Your #%s account received %s AZN";

        return new NotificationDto(
                u.getId(),
                title,
                String.format(content, u.getFirstName(), u.getLastName(), account.getAccountNumber(), amount)
        );
    }
}
