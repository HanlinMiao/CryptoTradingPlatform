package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.TwoFactorOTP;
import com.hanlin.TradingPlatform.model.User;

public interface TwoFactorOTPService {
    TwoFactorOTP createTwoFactorOPT(User user, String otp, String jwt);

    TwoFactorOTP findByUser(Long userId);

    TwoFactorOTP findById(String id);

    boolean verifyTwoFactorOTP(TwoFactorOTP twoFactorOTP, String otp);

    void deleteTwoFactorOTP(TwoFactorOTP twoFactorOTP);

}
