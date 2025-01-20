package com.hanlin.TradingPlatform.service;


import com.hanlin.TradingPlatform.config.JwtProvider;
import com.hanlin.TradingPlatform.model.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserServiceImpl implements UserService{

    @Override
    public User findUserProfileByJwt(String jwt) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public User findUserById(Long userId) {
        return null;
    }

    @Override
    public User enableTwoFactorAuthentication(User user) {
        return null;
    }

    @Override
    public User updatePassword(User user, String newPassword) {
        return null;
    }
}
