package dev.Innocent.Flight.Services.Utils;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {

    private static Map<String, Double> paymentMap = new HashMap<>();

    static {
        paymentMap.put("acc1", 12000.0);
        paymentMap.put("acc2", 10000.0);
        paymentMap.put("acc3", 5000.0);
        paymentMap.put("acc4", 8000.0);
    }

    public static boolean validateCreditLimit(String accNo, double paidAmount){
        if(paidAmount> paymentMap.get(accNo)){
            throw new RuntimeException("Insufficient Balance");
        }else{
            return true;
        }
    }
}
