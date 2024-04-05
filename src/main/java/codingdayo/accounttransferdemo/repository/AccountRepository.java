package codingdayo.accounttransferdemo.repository;

import codingdayo.accounttransferdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean existsByName(String name);

    Boolean existsByAccountNumber(String accountNumber);

    Account findByAccountNumber(String accountNumber);

}
