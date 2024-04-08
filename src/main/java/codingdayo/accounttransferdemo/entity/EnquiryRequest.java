package codingdayo.accounttransferdemo.entity;

import lombok.*;

import java.math.BigDecimal;

  @Data
    @NoArgsConstructor
    @AllArgsConstructor
  @Builder
    public class EnquiryRequest {

        private String accountNumber;

    }
