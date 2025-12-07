package com.example.bankingapi.service;
import com.example.bankingapi.entity.Account;
import java.util.List;

public interface AccountService {
    public Account createAccount(Account account);
    public Account getAccountDetailsById(Long id);
    public List<Account> getAllAccountDetails();
    public Account depositAmount(Long id, double amount);
    public Account withdrawAmount(Long id, double amount);
    public void deleteAccount(Long id);

}
