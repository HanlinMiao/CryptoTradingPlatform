package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.PaymentDetails;
import com.hanlin.TradingPlatform.model.User;

public interface PaymentDetailsService {
    public PaymentDetails addPaymentDetails(String accountNumber,
                                            String accountHolderName,
                                            String ifsc,
                                            String bankName,
                                            User user);

    public PaymentDetails getUserPaymentDetails(User user);
}
