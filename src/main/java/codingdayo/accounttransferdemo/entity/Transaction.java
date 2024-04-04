package codingdayo.accounttransferdemo.entity;

import jakarta.persistence.Entity;
import lombok.*;

import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

        private String fromAccountNumber;

        private String toAccountNumber;

        private BigDecimal amount;


}
