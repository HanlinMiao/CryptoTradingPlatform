package com.hanlin.TradingPlatform.controller;

import com.hanlin.TradingPlatform.domain.PaymentMethod;
import com.hanlin.TradingPlatform.model.PaymentOrder;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.response.PaymentResponse;
import com.hanlin.TradingPlatform.service.PaymentOrderService;
import com.hanlin.TradingPlatform.service.UserService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PaymentOrderController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentOrderService paymentOrderService;

    @PostMapping("/api/payment/{paymentMethod}/amount/{amount}")
    public ResponseEntity<PaymentResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long amount,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        PaymentResponse paymentResponse;
        PaymentOrder paymentOrder = paymentOrderService.createOrder(user, amount, paymentMethod);

        if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
            paymentResponse = paymentOrderService.createRazorpayPaymentLink(user, amount);
        } else {
            paymentResponse = paymentOrderService.createStripePaymentLink(user, amount, paymentOrder.getId());
        }

        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
}
