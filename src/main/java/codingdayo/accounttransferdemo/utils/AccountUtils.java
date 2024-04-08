package codingdayo.accounttransferdemo.utils;

import java.time.Year;

public class AccountUtils {

    public static final String ACCOUNT_NOT_EXIST_CODE = "001";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE = "THIS ACCOUNT NUMBER DOES NOT EXIST";

    public static final String ACCOUNT_CREDITED_SUCCESS = "002";
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE = "USER ACCOUNT CREDITED";

    public static final String INSUFFICIENT_BALANCE_CODE = "003";
    public static final String INSUFFICIENT_BALANCE_MESSAGE = "INSUFFICIENT BALANCE";

    public static final String ACCOUNT_DEBITED_SUCCESS = "004";
    public static final String ACCOUNT_DEBITED_SUCCESS_MESSAGE = "USER ACCOUNT DEBITED";

    public static final String TRANSFER_SUCCESSFUL_CODE = "005";
    public static final String TRANSFER_SUCCESSFUL_MESSAGE = "TRANSFER SUCCESSFUL";


    public static String generateAccountNumber(){

        Year currentYear = Year.now();

        int min = 10000;

        int max = 99999;

        int randomNo = (int) Math.floor(Math.random() * (max - min + 1) + min);

        String year = String.valueOf(currentYear);

        String randomNumber = String.valueOf(randomNo);

        StringBuilder accountNumber = new StringBuilder();

        return  accountNumber.append(year).append(randomNumber).toString();





    }
}
