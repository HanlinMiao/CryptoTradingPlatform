package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.domain.VerificationType;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.VerificationCode;

public interface VerificationCodeService {

    VerificationCode sendVerificationCode(User user, VerificationType verificationType);

    VerificationCode getVerificationCodeById(Long id) throws Exception;

    VerificationCode getVerificationCodeByUser(Long userId);

    void deleteVerificationCode(VerificationCode verificationCode);

}
