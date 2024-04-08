package codingdayo.accounttransferdemo.service;

import codingdayo.accounttransferdemo.entity.Account;

import codingdayo.accounttransferdemo.entity.EnquiryRequest;
import codingdayo.accounttransferdemo.repository.AccountRepository;
import codingdayo.accounttransferdemo.utils.AccountUtils;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountRepository accountRepository;



    @Override
    public Account findById(Long theId) {


            Optional<Account> optionalAccount = accountRepository.findById(theId);

            Account theAccount = null;

            if (optionalAccount.isPresent()) {
                theAccount = optionalAccount.get();
            }
            else {

                throw new RuntimeException("Did not find Account with id - " + theId);
            }

            return theAccount;

            //this didn't work because theBook.get() didn't have isPresent().
            //Optional<Book> theBook = bookRepository.findById(theId);
            //
            //
            //
            //return theBook.get();
    }

    @Override
    public Account createAccount(Account account) {

        //This prevents duplication
        if(accountRepository.existsByName(account.getName())){
            throw new RuntimeException("Account Already exists");
        }

        Account newAccount = Account.builder()
                .name(account.getName())
                .accountNumber(AccountUtils.generateAccountNumber())
                .accountBalance(BigDecimal.ZERO)
                .build();

        return accountRepository.save(newAccount);

    }

    @Override
    public Account deposit(Long id, BigDecimal amount) {
        Account theAccount = accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("This Account doesn't exist"));

        BigDecimal balance = theAccount.getAccountBalance().add(amount);
        theAccount.setAccountBalance(balance);

        return accountRepository.save(theAccount);
    }

    @Override
    public Account withdraw(Long id, BigDecimal amount) {
        Account theAccount = accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("This Account doesn't exist"));

        BigDecimal balance = theAccount.getAccountBalance().subtract(amount);
        theAccount.setAccountBalance(balance);

        return accountRepository.save(theAccount);
    }

    @Override
    public void deleteById(Long id) {

        Account theAccount = accountRepository.findById(id).orElseThrow(()
                -> new RuntimeException("This Account doesn't exist"));

         accountRepository.deleteById(id);
    }

    @Override
    public Account balanceEnquiry(EnquiryRequest request) {
        Boolean isAccountExist = accountRepository.existsByAccountNumber(request.getAccountNumber());

        if(!isAccountExist){
            throw new RuntimeException("Account does not exists");
        }

        Account foundAccount = accountRepository.findByAccountNumber(request.getAccountNumber());
        return Account.builder()
                .name(foundAccount.getName())
                .accountNumber(foundAccount.getAccountNumber())
                .accountBalance(foundAccount.getAccountBalance())
                .build();
    }


    //public AccountDto deposit(int id, double amount) {
    //
    //    Account theAccount = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Can't find the account"));
    //
    //    double theTotal = theAccount.getAccountBalance() + amount;
    //    theAccount.setAccountBalance(theTotal);
    //    Account savedAccount = accountRepository.save(theAccount);
    //    return AccountMapper.mapToAccountDto(savedAccount);
    //}
}
