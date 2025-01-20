package com.hanlin.TradingPlatform.request;

import com.hanlin.TradingPlatform.domain.VerificationType;
import lombok.Data;

@Data
public class ForgotPasswordTokenRequest {
    private String sendTo;
    private String otp;
    private VerificationType verificationType;
}
