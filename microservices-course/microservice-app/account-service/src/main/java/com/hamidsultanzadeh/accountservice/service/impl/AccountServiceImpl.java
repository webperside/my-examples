package com.hamidsultanzadeh.accountservice.service.impl;

import com.hamidsultanzadeh.accountservice.dao.AccountDaoInter;
import com.hamidsultanzadeh.accountservice.entity.Account;
import com.hamidsultanzadeh.accountservice.service.inter.AccountServiceInter;
import com.hamidsultanzadeh.client.contract.AccountDTO;
import org.modelmapper.ModelMapper;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountServiceInter {

    private final AccountDaoInter accountDaoInter;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountDaoInter accountDaoInter,ModelMapper modelMapper) {
        this.accountDaoInter = accountDaoInter;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountDTO findById(String id) {
        Optional<Account> account = accountDaoInter.findById(id);
        return account.map(ac -> modelMapper.map(account.get(),AccountDTO.class)).orElse(null);
    }

    @Override
    public AccountDTO save(AccountDTO accountDTO) {
        Account ac = modelMapper.map(accountDTO,Account.class);
        ac.setEnabled(true);
        ac.setId(UUID.randomUUID().toString());
        ac = accountDaoInter.save(ac);
        accountDTO.setId(ac.getId());
        return accountDTO;
    }

    @Override
    public AccountDTO delete(String id) {
        Optional<Account> ac = accountDaoInter.findById(id);
        AccountDTO accountDTO = ac.map(account -> modelMapper.map(account, AccountDTO.class)).orElse(null);
        ac.ifPresent(accountDaoInter::delete);
        return accountDTO;
    }

    @Override
    public AccountDTO update(AccountDTO accountDTO) {
        Optional<Account> ac = accountDaoInter.findById(accountDTO.getId());
        ac.ifPresent(a ->{
            a.setUsername(accountDTO.getUsername());
            a.setName(accountDTO.getName());
            a.setSurname(accountDTO.getSurname());
            a.setDateOfBirth(accountDTO.getDateOfBirth());
            a.setEmail(accountDTO.getEmail());
        });

        return ac.map(a -> modelMapper.map(accountDaoInter.save(a),AccountDTO.class)).orElse(null);
    }

    @Override
    public List<AccountDTO> findAll(Integer page){
        Slice<Account> accounts = accountDaoInter.findAll(CassandraPageRequest.of(0,10));

        while(accounts.hasNext() &&  page-- > 0){
            accounts = accountDaoInter.findAll(accounts.nextPageable());
        }

        return accounts
                .stream()
                .map(account -> modelMapper.map(account,AccountDTO.class)).collect(Collectors.toList());
    }
}
