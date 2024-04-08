package codingdayo.accounttransferdemo.service;

import codingdayo.accounttransferdemo.entity.Account;

import codingdayo.accounttransferdemo.entity.AccountResponse;
import codingdayo.accounttransferdemo.entity.EnquiryRequest;
import codingdayo.accounttransferdemo.entity.TransferRequest;
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
                .id(foundAccount.getId())
                .name(foundAccount.getName())
                .accountNumber(foundAccount.getAccountNumber())
                .accountBalance(foundAccount.getAccountBalance())
                .build();
    }

    @Override
    public AccountResponse transfer(TransferRequest request) {
        //Get the account to debit
        //check if the amount I'm debiting is not more than the current balance
        //debit the account
        //get the account to credit
        //credit the acccount

        boolean isDestinationAccountExist = accountRepository.existsByAccountNumber(request.getDestinationAccountNumber());

        if(!isDestinationAccountExist){
            return AccountResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        Account getSourceAccount = accountRepository.findByAccountNumber(request.getSourceAccountNumber());
        if(request.getAmount().compareTo(getSourceAccount.getAccountBalance()) > 0){
            return AccountResponse.builder()
                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        getSourceAccount.setAccountBalance(getSourceAccount.getAccountBalance().subtract(request.getAmount()));
        accountRepository.save(getSourceAccount);

        Account getDestinationAccount = accountRepository.findByAccountNumber(request.getDestinationAccountNumber());
        getDestinationAccount.setAccountBalance(getDestinationAccount.getAccountBalance().add(request.getAmount()));
        accountRepository.save(getDestinationAccount);

        return AccountResponse.builder()
                .responseCode(AccountUtils.TRANSFER_SUCCESSFUL_CODE)
                .responseMessage(AccountUtils.TRANSFER_SUCCESSFUL_MESSAGE)
                .accountInfo(null)
                .build();


    }


}
