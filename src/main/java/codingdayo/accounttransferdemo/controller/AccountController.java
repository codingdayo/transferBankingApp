package codingdayo.accounttransferdemo.controller;

import codingdayo.accounttransferdemo.entity.Account;
import codingdayo.accounttransferdemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/api/user")
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("{id}")
    public ResponseEntity<Account> findById(@PathVariable("id") Long id){

        Account theAccount = accountService.findById(id);

        return new ResponseEntity<>(theAccount, HttpStatus.OK);

    }

    @PutMapping("{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request){

        BigDecimal amount = request.get("amount");

        Account newBalance = accountService.deposit(id, amount);

        return ResponseEntity.ok(newBalance);

    }

    @PutMapping("{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request){

        BigDecimal amount = request.get("amount");

        Account newBalance = accountService.withdraw(id, amount);

        return ResponseEntity.ok(newBalance);

    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){

        Account newAccount = accountService.createAccount(account);

        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){

        accountService.deleteById(id);

        return  ResponseEntity.ok("Account has been deleted successfully");
    }

}
