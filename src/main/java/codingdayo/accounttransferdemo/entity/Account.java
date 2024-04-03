package codingdayo.accounttransferdemo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table (name = "bank_Transfer")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;

    private String accountNumber;

    private BigDecimal accountBalance;

}
