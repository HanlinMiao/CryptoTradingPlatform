package com.hanlin.TradingPlatform.controller;

import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.model.Wallet;
import com.hanlin.TradingPlatform.model.Withdrawal;
import com.hanlin.TradingPlatform.service.UserService;
import com.hanlin.TradingPlatform.service.WalletService;
import com.hanlin.TradingPlatform.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/withdrawal")
public class WithdrawalController {
    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping("/api/withdrawal/{amount}")
    public ResponseEntity<?> withdrawalRequest(
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Wallet wallet = walletService.getUserWallet(user);

        Withdrawal withdrawal = withdrawalService.requestWithdrawal(amount, user);
        walletService.addBalance(wallet, -withdrawal.getAmount());

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @PatchMapping("/api/admin/withdrawal/{id}/proceed/{accept}")
    public ResponseEntity<?> proceedWithdrawal(
            @PathVariable Long id,
            @PathVariable boolean accept,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Withdrawal withdrawal = withdrawalService.proceedWithWithdrawal(id, accept);
        Wallet wallet = walletService.getUserWallet(user);

        if (!accept) {
            walletService.addBalance(wallet, withdrawal.getAmount());
        }

        return new ResponseEntity<>(withdrawal, HttpStatus.OK);
    }

    @GetMapping("/api/withdrawal")
    public ResponseEntity<List<Withdrawal>> getWithdrawalHistory(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawals = withdrawalService.getUserWithdrawalHistory(user);
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }

    @GetMapping("/api/admin/withdrawal")
    public ResponseEntity<List<Withdrawal>> getAllWithdrawalRequest(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        List<Withdrawal> withdrawals = withdrawalService.getAllWithdrawalRequest();
        return new ResponseEntity<>(withdrawals, HttpStatus.OK);
    }
}
