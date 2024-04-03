package codingdayo.accounttransferdemo.controller;

import codingdayo.accounttransferdemo.entity.Account;
import codingdayo.accounttransferdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        Account newAccount = accountService.createAccount(account);


        return new ResponseEntity<>(newAccount, HttpStatus.OK);

    }
}
