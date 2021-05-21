package com.hamidsultanzadeh.accountservice.service.inter;

import com.hamidsultanzadeh.client.contract.AccountDTO;

import java.util.List;

public interface AccountServiceInter {

    AccountDTO findById(String id);

    AccountDTO save(AccountDTO accountDTO);

    AccountDTO delete(String id);

    AccountDTO update(AccountDTO accountDTO);

    List<AccountDTO> findAll(Integer page);

}
