package com.hanlin.TradingPlatform.service;

import com.hanlin.TradingPlatform.domain.PaymentMethod;
import com.hanlin.TradingPlatform.model.PaymentOrder;
import com.hanlin.TradingPlatform.model.User;
import com.hanlin.TradingPlatform.response.PaymentResponse;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentOrderService {

    PaymentOrder createOrder(User user, Long amount, PaymentMethod paymentMethod);

    PaymentOrder getPaymentOrderById(Long id) throws Exception;

    Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId) throws RazorpayException;

    PaymentResponse createRazorpayPaymentLink(User user, Long amount, Long orderId) throws RazorpayException;

    PaymentResponse createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException;
}
