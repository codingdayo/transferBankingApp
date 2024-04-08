package codingdayo.accounttransferdemo.service;

import codingdayo.accounttransferdemo.entity.Account;
import codingdayo.accounttransferdemo.entity.AccountResponse;
import codingdayo.accounttransferdemo.entity.EnquiryRequest;
import codingdayo.accounttransferdemo.entity.TransferRequest;


import java.math.BigDecimal;

public interface AccountService {


    Account findById(Long theId);

    Account createAccount(Account account);

    Account deposit(Long id, BigDecimal amount);

    Account withdraw(Long id, BigDecimal amount);

    void deleteById(Long id);

    Account balanceEnquiry(EnquiryRequest request);

    AccountResponse transfer(TransferRequest request);





}
