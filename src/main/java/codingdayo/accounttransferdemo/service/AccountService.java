package codingdayo.accounttransferdemo.service;

import codingdayo.accounttransferdemo.entity.Account;

import java.math.BigDecimal;

public interface AccountService {


    Account findById(Long theId);

    Account createAccount(Account account);

    Account deposit(Long id, BigDecimal amount);

    Account withdraw(Long id, BigDecimal amount);

    void deleteById(Long id);


}
