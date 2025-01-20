package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.domain.VerificationType;
import com.hanlin.TradingPlatform.model.ForgotPasswordToken;
import com.hanlin.TradingPlatform.model.User;

public interface ForgotPasswordService {

    ForgotPasswordToken createToken(User user, String id, String otp, VerificationType verificationType, String sendTo);

    ForgotPasswordToken findById(String id);

    ForgotPasswordToken findByUser(Long userId);

    void deleteToken(ForgotPasswordToken token);
}
