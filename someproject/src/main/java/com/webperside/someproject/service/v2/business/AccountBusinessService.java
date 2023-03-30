package com.webperside.someproject.service.v2.business;

import com.webperside.someproject.model.dto.NotificationDto;
import com.webperside.someproject.model.mybatis.Account;
import com.webperside.someproject.model.mybatis.User;
import com.webperside.someproject.service.v2.functional.AccountServiceV2;
import com.webperside.someproject.service.v2.functional.NotificationServiceV2;
import com.webperside.someproject.service.v2.functional.UserServiceV2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountBusinessService {

    private final AccountServiceV2 accountService; // functional
    private final UserServiceV2 userService;// functional
    private final NotificationServiceV2 notificationService;// functional

    public AccountBusinessService(AccountServiceV2 accountService, UserServiceV2 userService, NotificationServiceV2 notificationService) {
        this.accountService = accountService;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public void transferMoney(long fromId, long toId, BigDecimal amount) {
        Account from = accountService.findAccountById(fromId);

        if (from.getAmount().compareTo(amount) < 0) {
            throw new RuntimeException("Account doesn't have enough money");
        }

        accountService.transferMoney(fromId, toId, amount);

        Account to = accountService.findAccountById(toId);

        notificationService.send(
                prepareNotificationDto(
                        userService.findUserById(to.getUserId()),
                        to,
                        amount
                )
        );
    }

    public void delete(long id) {
        Account account = accountService.findAccountById(id);

        if (account.getAmount().equals(BigDecimal.ZERO)) {
            throw new RuntimeException("Account cannot be deleted");
        }

        accountService.delete(id);
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
