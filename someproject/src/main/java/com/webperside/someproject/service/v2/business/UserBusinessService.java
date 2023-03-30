package com.webperside.someproject.service.v2.business;

import com.webperside.someproject.model.enums.UserStatus;
import com.webperside.someproject.model.mybatis.Account;
import com.webperside.someproject.model.mybatis.User;
import com.webperside.someproject.model.request.UserRequest;
import com.webperside.someproject.model.response.AccountResponse;
import com.webperside.someproject.model.response.UserResponse;
import com.webperside.someproject.service.v2.functional.AccountServiceV2;
import com.webperside.someproject.service.v2.functional.UserServiceV2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBusinessService {

    private final AccountServiceV2 accountService;
    private final UserServiceV2 userService; // functional

    public UserBusinessService(AccountServiceV2 accountService, UserServiceV2 userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    // task 1
    public UserResponse getUserDataById(Long id) {
        User user = userService.findUserById(id);

        List<Account> eligibleAccounts = accountService.getAllEligibleAccountsByUserId(user.getId());

        return prepareResponse(user, eligibleAccounts);
    }

    // task 1.1
    public List<AccountResponse> getAllAccountsByUserId(long id) {
        return prepareAccountResponse(
                accountService.getAllAccountsByUserId(id)
        );
    }

    // task 2
    public void approveUser(long id) {
        User user = userService.findUserById(id);

        // checking all accounts are eligible
        if (accountService.getAllEligibleAccountsByUserId(user.getId()).size() > 0) {
            user.setUserStatus(UserStatus.APPROVED);
            return;
        }

        throw new RuntimeException("User's accounts are not eligible");
    }

    // task 3
    public void createUser(UserRequest request) {
        long insertedUserId = userService.createUser(request.toUser());

        accountService.saveAllAccounts(insertedUserId, request.getAccounts());
    }

    // private util methods

    private UserResponse prepareResponse(User user, List<Account> eligibleAccounts) {
        return new UserResponse(
                user.getId(), user.getFirstName(), user.getLastName(), user.getUserStatus(), prepareAccountResponse(eligibleAccounts)
        );
    }

    private List<AccountResponse> prepareAccountResponse(List<Account> accounts) {
        List<AccountResponse> accountsResp = new ArrayList<>();
        for (Account acc : accounts) {
            accountsResp.add(
                    new AccountResponse(acc.getId(), acc.getAccountNumber(), acc.getAmount(), acc.getUserId())
            );
        }
        return accountsResp;
    }
}
