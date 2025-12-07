package com.example.bankingapi.controller;
import com.example.bankingapi.entity.Account;
import com.example.bankingapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount=accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }
    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id){
        Account account = accountService.getAccountDetailsById(id);
        return account;
    }
    @GetMapping("/getAllAccounts")
    public List<Account> getAllAccountDetails(){
        List<Account> listOfAccounts = accountService.getAllAccountDetails();
        return listOfAccounts;
    }
    @PutMapping("/deposit/{id}/{amount}")
    public Account depositAccount(@PathVariable Long id, @PathVariable Double amount){
        Account account = accountService.depositAmount(id,amount);
        return account;

    }
    @PutMapping("/withdraw/{id}/{amount}")
    public Account withdrawAmount(@PathVariable Long id, @PathVariable Double amount){
        Account account = accountService.withdrawAmount(id,amount);
        return account;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account was deleted");

    }

}
