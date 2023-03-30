package com.webperside.someproject.service;

import com.webperside.someproject.model.enums.UserStatus;
import com.webperside.someproject.model.mybatis.Account;
import com.webperside.someproject.model.mybatis.User;
import com.webperside.someproject.model.request.UserRequest;
import com.webperside.someproject.model.response.AccountResponse;
import com.webperside.someproject.model.response.UserResponse;
import com.webperside.someproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    // task 1
    public UserResponse getUserDataById(Long id) {
        User user = findUserById(id);

        List<Account> eligibleAccounts = accountService.getAllEligibleAccountsByUserId(id);

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
        User user = findUserById(id);

        // checking all accounts are eligible
        if (accountService.getAllEligibleAccountsByUserId(id).size() > 0) {
            user.setUserStatus(UserStatus.APPROVED);
            return;
        }

        throw new RuntimeException("User's accounts are not eligible");
    }

    // task 3
    public void createUser(UserRequest request) {
        long insertedUserId = userRepository.save(request.toUser());

        accountService.saveAllAccounts(insertedUserId, request.getAccounts());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
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
