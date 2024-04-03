package codingdayo.accounttransferdemo.repository;

import codingdayo.accounttransferdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
