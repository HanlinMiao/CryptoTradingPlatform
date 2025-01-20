package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    public VerificationCode findByUserId(Long userId);

}
