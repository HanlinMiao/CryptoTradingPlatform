package com.hanlin.TradingPlatform.repository;

import com.hanlin.TradingPlatform.model.ForgotPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPasswordToken, String> {

    ForgotPasswordToken findByUserId(Long userId);
}
