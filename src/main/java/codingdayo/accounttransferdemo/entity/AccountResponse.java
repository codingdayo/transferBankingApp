package codingdayo.accounttransferdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponse {

    private String responseCode;
    private String responseMessage;

    private AccountInfo accountInfo;
}
