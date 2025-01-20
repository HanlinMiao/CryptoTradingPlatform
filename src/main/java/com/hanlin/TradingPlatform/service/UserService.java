package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.domain.VerificationType;
import com.hanlin.TradingPlatform.model.User;

public interface UserService {

    public User findUserProfileByJwt(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
    public User findUserById(Long userId) throws Exception;

    public User enableTwoFactorAuthentication(
            VerificationType verificationType,
            String sendTo,
            User user
    );
    User updatePassword(User user, String newPassword);
}
