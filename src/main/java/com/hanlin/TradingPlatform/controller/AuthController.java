package com.hanlin.TradingPlatform.controller;

import com.hanlin.TradingPlatform.config.JwtProvider;
import com.hanlin.TradingPlatform.model.TwoFactorOTP;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.repository.UserRepository;
import com.hanlin.TradingPlatform.response.AuthResponse;
import com.hanlin.TradingPlatform.service.CustomUserDetailService;
import com.hanlin.TradingPlatform.service.EmailService;
import com.hanlin.TradingPlatform.service.TwoFactorOTPService;
import com.hanlin.TradingPlatform.utils.OTPUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private TwoFactorOTPService twoFactorOTPService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) throws Exception {
        // Check if email address already exists
        User isEmailExist = userRepository.findByEmail(user.getEmail());
        if (isEmailExist!=null){
            throw new Exception("Email is already is in use");
        }

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setFullName(user.getFullName());

        // .save() saves the user object to the database
        User savedUser = userRepository.save(newUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                user.getPassword()

        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("register success");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> login(@RequestBody User user) throws Exception {

        String userName = user.getEmail();
        String password = user.getPassword();

        Authentication auth = authenticate(userName, password);
        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = JwtProvider.generateToken(auth);


        User authUser = userRepository.findByEmail(userName);

        if (user.getTwoFactorAuth().isEnabled()) {
            AuthResponse response = new AuthResponse();
            response.setMessage("Two-Factor Authentication is enabled");
            response.setTwoFactorAuthEnabled(true);
            String otp = OTPUtils.generateOTP();

            TwoFactorOTP oldTwoFactorOTP = twoFactorOTPService.findByUser(authUser.getId());

            if (oldTwoFactorOTP != null) {
                twoFactorOTPService.deleteTwoFactorOTP(oldTwoFactorOTP);
            }
            TwoFactorOTP newTwoFactorOTP = twoFactorOTPService.createTwoFactorOPT(authUser, otp, jwt);
            emailService.sendVerificationEmail(userName, otp);

            response.setSession(newTwoFactorOTP.getId());
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        }

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setStatus(true);
        authResponse.setMessage("login success");

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    private Authentication authenticate(String userName, String password) throws Exception {
        UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (! password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    public ResponseEntity<AuthResponse> verifySigningOTP(
            @PathVariable String otp,
            @RequestParam String id) {
        // the id comes from the TwoFactorOTP class

        TwoFactorOTP twoFactorOTP = twoFactorOTPService.findById(id);
        if (twoFactorOTPService.verifyTwoFactorOTP(twoFactorOTP, otp)) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setMessage("Two-Factor authentication verified");
            authResponse.setTwoFactorAuthEnabled(true);
            authResponse.setJwt(twoFactorOTP.getJwt());
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        }
        throw new BadCredentialsException("Invalid otp");
    }
}
