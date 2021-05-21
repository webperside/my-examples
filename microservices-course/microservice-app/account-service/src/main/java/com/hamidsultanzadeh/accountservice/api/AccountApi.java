package com.hamidsultanzadeh.accountservice.api;

import com.hamidsultanzadeh.accountservice.service.inter.AccountServiceInter;
import com.hamidsultanzadeh.client.contract.AccountDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountApi {

    private final AccountServiceInter accountServiceInter;

    // or use Lombok for set accountServiceInter
    public AccountApi(AccountServiceInter accountServiceInter) {
        this.accountServiceInter = accountServiceInter;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> get(@PathVariable("id") String id){

        return ResponseEntity.ok(accountServiceInter.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDTO> save(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(accountServiceInter.save(accountDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDTO> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(accountServiceInter.delete(id));
    }

    @PutMapping
    public ResponseEntity<AccountDTO> update(@RequestBody AccountDTO accountDTO){
        return ResponseEntity.ok(accountServiceInter.update(accountDTO));
    }

    @GetMapping
    public ResponseEntity<List<AccountDTO>> findAll(@RequestParam(name = "page") Integer p){
        return ResponseEntity.ok(accountServiceInter.findAll(--p));
    }

}
