package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.Withdrawal;

import java.util.List;

public interface WithdrawalService {

    Withdrawal requestWithdrawal(Long amount, User user);
    Withdrawal proceedWithWithdrawal(Long withdrawId, boolean accept) throws Exception;
    List<Withdrawal> getUserWithdrawalHistory(User user);
    List<Withdrawal> getAllWithdrawalRequest();
}
