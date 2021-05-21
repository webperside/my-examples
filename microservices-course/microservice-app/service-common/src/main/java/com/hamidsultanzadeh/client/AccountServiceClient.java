package com.hamidsultanzadeh.client;

import com.hamidsultanzadeh.client.contract.AccountDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("account-service")
@RequestMapping("/api/accounts")
public interface AccountServiceClient {

    @GetMapping("/{id}")
    ResponseEntity<AccountDTO> get(@PathVariable("id") String id);
}
