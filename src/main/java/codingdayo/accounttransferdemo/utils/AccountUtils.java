package codingdayo.accounttransferdemo.utils;

import java.time.Year;

public class AccountUtils {

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
