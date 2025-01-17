package com.hanlin.TradingPlatform.model;

import com.hanlin.TradingPlatform.domain.VerificationType;
import lombok.Data;

@Data
public class TwoFactorAuth {
    private boolean isEnabled = false;
    private VerificationType sendTo;
}
