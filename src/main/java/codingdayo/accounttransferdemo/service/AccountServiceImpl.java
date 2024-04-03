package codingdayo.accounttransferdemo.service;

import codingdayo.accounttransferdemo.entity.Account;
import codingdayo.accounttransferdemo.repository.AccountRepository;
import codingdayo.accounttransferdemo.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(Account account) {




        Account newAccount = Account.builder()
                .name(account.getName())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .build();

        return accountRepository.save(newAccount);

    }
}
