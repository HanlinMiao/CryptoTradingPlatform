package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.model.Order;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.Wallet;

public interface WalletService {
    Wallet getUserWallet(User user);

    Wallet addBalance(Wallet wallet, Long amount);

    Wallet findWalletById(Long id) throws Exception;

    Wallet walletToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception;

    Wallet payOrderPayment(Order order, User user) throws Exception;

}
