package com.example.bankingapi.service;
import com.example.bankingapi.entity.Account;
import com.example.bankingapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account createAccount(Account account) {
        Account savedAccount = accountRepository.save(account);
        return savedAccount;
    }

    @Override
    public Account getAccountDetailsById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
            Account account_found = account.get();
            return account_found;
    }

    @Override
    public List<Account> getAllAccountDetails() {
        List<Account> listOfAccounts = accountRepository.findAll();
        return listOfAccounts;

    }

    @Override
    public Account depositAmount(Long id, double amount) {
       Optional<Account> account = accountRepository.findById(id);
       if (account.isEmpty()) {
           throw new RuntimeException("Account not found");
       }
       Account accountPresent= account.get();
       Double totalBalance = accountPresent.getAccountBalance()+amount;
       accountPresent.setAccountBalance(totalBalance);
       accountRepository.save(accountPresent);
       return accountPresent;

    }

    @Override
    public Account withdrawAmount(Long id, double amount) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        Account accountPresent= account.get();
        Double accountBalance = accountPresent.getAccountBalance()-amount;
        accountPresent.setAccountBalance(accountBalance);
        accountRepository.save(accountPresent);
        return accountPresent;
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if(account.getAccountBalance()>0){
            throw new RuntimeException("Account Balance is not zero");
        }
        accountRepository.delete(account);
    }

}
