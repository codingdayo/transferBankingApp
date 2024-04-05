package codingdayo.accounttransferdemo.entity;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

        private Long transactionId;

        private String accountNumber;

        private BigDecimal transactionAmount;

        private Timestamp transactionDateTime;


}
